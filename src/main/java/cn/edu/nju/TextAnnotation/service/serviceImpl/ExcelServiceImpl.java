package cn.edu.nju.TextAnnotation.service.serviceImpl;


import cn.edu.nju.TextAnnotation.bean.FactListBean;
import cn.edu.nju.TextAnnotation.bean.JudgementDto;
import cn.edu.nju.TextAnnotation.bean.StatuteListBean;
import cn.edu.nju.TextAnnotation.model.*;
import cn.edu.nju.TextAnnotation.repository.*;
import cn.edu.nju.TextAnnotation.service.ExcelService;
import cn.edu.nju.TextAnnotation.util.ImportExcelUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.util.ClassUtils;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@Service
public class ExcelServiceImpl implements ExcelService {
    @Autowired
    private StatuteRepository statuteRepository;
    @Autowired
    private Law1ArticleRepository law1ArticleRepository;
    @Autowired
    private InstrumentRepository instrumentRepository;
    @Autowired
    private InstrumentAndStatueRepository instrumentAndStatueRepository;
    @Autowired
    private FactRepository factRepository;
    @Autowired
    private JudgementRepository judgementRepository;
    /**
     * 上传excel文件到临时目录后并开始解析
     * @param files
     * @return
     */
    @Override
    public String batchImport(List<MultipartFile> files){
        MultipartFile file = null;
        String message = "";
        //获取项目绝对路径
        String path=this.getClass().getClassLoader().getResource("").getPath();
        File uploadDir = new  File(path);
        //创建一个目录 （它的路径名由当前 File 对象指定，包括任一必须的父路径。）
        if (!uploadDir.exists()) uploadDir.mkdirs();
        for (int i = 0; i < files.size(); ++i) {
            file = files.get(i);
            //判断文件是否为空
            if (file == null) {
                message += "文件不能为空！";
                continue;
            }
                //获取文件名
                String fileName = file.getOriginalFilename();
                //验证文件名是否合格
                if (!ImportExcelUtil.validateExcel(fileName)) {
                    message += "文件必须是excel格式！";
                    continue;
                }
                    //批量导入
                    //新建一个文件
                    File tempFile = new File(path + fileName );
                    //初始化输入流
                    InputStream is = null;
                    try{
                        //将上传的文件写入新建的文件中
                        file.transferTo(tempFile);
                        //根据新建的文件实例化输入流
                        is = new FileInputStream(tempFile);
                        //根据版本选择创建Workbook的方式
                        Workbook wb = null;
                        //根据文件名判断文件是2003版本还是2007版本
                        if(ImportExcelUtil.isExcel2007(fileName)){
                            wb = new XSSFWorkbook(is);
                        }else{
                            wb = new HSSFWorkbook(is);
                        }
                        //根据excel里面的内容读取知识库信息
                        message += saveIntoInstrumentAndStatute(wb,fileName);
                        message += saveIntoInstrumentAndStatuteAndFact(wb,fileName);
                    }catch(Exception e){
//            try {
//                File f = new File("C:\\Users\\czh\\Desktop\\log\\a.txt");
//                PrintStream stream = new PrintStream(f);
//                e.printStackTrace(stream);
//            }catch (Exception e2){
//                e2.printStackTrace();
//            }
                        e.printStackTrace();
//            return "导入出错！请检查数据格式！";
                        message += e.getMessage();
                    } finally{
                        if(is !=null)
                        {
                            try{
                                is.close();
                            }catch(IOException e){
                                is = null;
                                e.printStackTrace();
                                message += "导入出错！请检查数据格式！";
                            }
                        }
                        //删除上传的临时文件
                        if(tempFile.exists()){
                            tempFile.delete();
                        }
                    }
                    message += ",第"+ (i+1) +"个文件处理完毕!<br/>";
        }
        return message;
    }
    /**
     * 存文书表和法条表
     */
    @Override
    public String saveIntoInstrumentAndStatute(Workbook wb,String fileName)
    {
        //错误信息接收器
        String errorMsg = "";
        //成功条数计数
        int count=0;

        //存文书表
        Instrument instrument = readInstrument(fileName);
        count = saveInstrument(instrument,count);
        errorMsg = "存文书表"+ count +"条;";
        //得到第一个shell
        Sheet sheet=wb.getSheetAt(0);
        //得到Excel的行数
        int totalRows=sheet.getPhysicalNumberOfRows();
        //总列数
        int totalCells = 0;
        //得到Excel的列数(前提是有行数)，从第二行算起
        if(totalRows>=2 && sheet.getRow(0) != null){
            totalCells=sheet.getRow(0).getPhysicalNumberOfCells();
        }
        if (sheet != null) {
            if (totalRows >= 2 && sheet.getRow(0) != null) {
                Row row = sheet.getRow(0);
                count = 0;
                for (int colNum = 1; colNum <= totalCells; colNum++) {
                    if (row.getCell(colNum) != null) {
                        Statute statute = new Statute();
                        Cell text = sheet.getRow(0).getCell(colNum);
                        String name = getStatute_name(text.getStringCellValue());
                        statute.setName(name);//获得法条名称
                        statute.setText(text.getStringCellValue());//获得法条内容
                        //存法条表
                        count = saveStatuteWithLaw1Article(statute, count);
                    }
                }
                errorMsg += "存法条表" + count + "条;";
            }
        }
        return errorMsg;
    }
    /**
     * 存文书-法条表和事实表
     */
    @Override
    public String saveIntoInstrumentAndStatuteAndFact(Workbook wb,String fileName){
        //错误信息接收器
        String errorMsg = "";
        //成功条数计数
        int count=0;
        //存文书表
        Instrument instrument = readInstrument(fileName);
        //得到第一个shell
        Sheet sheet=wb.getSheetAt(0);
        //得到Excel的行数
        int totalRows=sheet.getPhysicalNumberOfRows();
        //总列数
        int totalCells = 0;
        //得到Excel的列数(前提是有行数)，从第二行算起
        if(totalRows>=2 && sheet.getRow(0) != null){
            totalCells=sheet.getRow(0).getPhysicalNumberOfCells();
        }
        if (sheet != null) {
            if (totalRows>=2 && sheet.getRow(0) != null) {
                Row row = sheet.getRow(0);
                count =0;
                for(int colNum = 1; colNum <= totalCells; colNum++) {
                    if(row.getCell(colNum) != null) {
                        InstrumentAndStatute instrumentAndStatute = new InstrumentAndStatute();
                        Cell text = row.getCell(colNum);
                        String name = getStatute_name(text.getStringCellValue());
                        instrumentAndStatute.setInstrumentid(instrument.getInstrumentid());//获得文书id
                        instrumentAndStatute.setNum(colNum);//获得法条在文书中顺序
                        //存文书-法条表
                        count = saveInstrumentAndStatute(instrumentAndStatute,name,count);
                    }
                }
                errorMsg += "存文书-法条表"+count+"条;";

                count =0;
                for(int rowNum = 1; rowNum <= totalRows; rowNum++) {
                    Row hssfRow = sheet.getRow(rowNum);
                    if (hssfRow != null) {
                        Fact fact = new Fact();
                        if(hssfRow.getCell(0)!= null) {
                            Cell text = hssfRow.getCell(0);
                            fact.setText(text.getStringCellValue());
                            fact.setNum(rowNum);
                            fact.setInstrumentid(instrument.getInstrumentid());
                            //存事实表
                            count =  saveFact(fact,count);
                        }
                    }
                }
                errorMsg += "存事实表"+count+"条";
            }
        }
        return errorMsg;
    }
    @Override
    public Integer saveStatuteWithLaw1Article(Statute statute,Integer count){
        String name = statute.getName();
        String docName = name.substring(0,name.indexOf("(")).trim();
        String articleSeq = name.substring(name.indexOf(")")+1,name.length()).trim();
//        Class tClass = law1ArticleRepository.getClass();
//        Method[] methods = tClass.getMethods();
//        Boolean has_method = false;
//        for (int i = 0; i < methods.length; i++) {
//            System.out.println("public method: " + methods[i]);
//            if (methods[i].getName().equals("findFirstByArticleSeqAndDocName")){
//                i = i;
//                has_method = true;
//            }
//        }
//        if(!has_method){
//            int i=0;
//        }

        Law1Article law1Article = law1ArticleRepository.findFirstByArticleSeqEqualsAndDocNameEquals(articleSeq,docName);
        if(law1Article != null) {
            String id = law1Article.getCode();
            statute.setStatuteid(id);//从law1Article表中取到Statuteid
            if (statuteRepository.findByStatuteid(id) == null)//判断法条表中是否已存在该条，若不存在，保存
            {
                statuteRepository.save(statute);
                count += 1;
            }
        }
        return count;
    }
    @Override
    public Integer saveInstrument(Instrument instrument,Integer count){
        if(instrumentRepository.findFirstByInstrumentid(instrument.getInstrumentid())==null)
        {
            instrumentRepository.save(instrument);
            count += 1;
        }
        return count;
    }
    @Override
    public Integer saveInstrumentAndStatute(InstrumentAndStatute instrumentAndStatute,String statute_name,Integer count){
        Statute statute =  statuteRepository.findFirstByName(statute_name);
        instrumentAndStatute.setStatuteid(statute.getStatuteid());
        if(instrumentAndStatueRepository.findFirstByInstrumentidAndStatuteid(instrumentAndStatute.getInstrumentid(),instrumentAndStatute.getStatuteid())==null)
        {
            instrumentAndStatueRepository.save(instrumentAndStatute);
            count += 1;
        }
        return count;
    }
    @Override
    public Integer saveFact(Fact fact,Integer count)
    {
        if(factRepository.findFirstByInstrumentidAndNum(fact.getInstrumentid(),fact.getNum())==null)
        {
            if(fact.getFactid()==null){
            factRepository.save(fact);
            count += 1;}
        }
        return count;
    }
    private Instrument readInstrument(String filename)  {
        Instrument instrument = new Instrument();
        //根据文件名截取文书序号，并转换为int
        int instrument_num =Integer.parseInt(getInstrumentNum(filename));
        //根据文件名截取文书xml
        String xml = getInstrumentXml(filename);
        instrument.setXml(xml);//获得文书名
        instrument.setInstrumentid(filename+"_xsys");//获得文书id
        instrument.setNum(instrument_num);//获得文书序号
        return instrument;
    }
    //根据_截取文件序号
    private String getInstrumentNum(String excelFileName){
        if(excelFileName.indexOf("_")!= -1) {
            excelFileName = excelFileName.substring(0, excelFileName.indexOf("_"));
        }
        return excelFileName;
    }
    //根据第一个_和最后一个_截取xml
    private String getInstrumentXml(String excelFileName){

        if(excelFileName.indexOf("_")!= -1 && excelFileName.lastIndexOf("_")!= -1) {
            excelFileName = excelFileName.substring(excelFileName.indexOf("_")+1,excelFileName.lastIndexOf("_"));
        }
        return excelFileName;
    }
    //根据：截取法条名称
    private String getStatute_name(String statute_name){

        if(statute_name.indexOf(":")!= -1) {
            statute_name = statute_name.substring(0, statute_name.indexOf(":"));
        }else if(statute_name.indexOf("：")!= -1)
        {
            statute_name = statute_name.substring(0, statute_name.indexOf("："));
        }
        return statute_name;
    }


    //导出excel部分
    //获取judgement表中数量最多的statute相关信息
    @Override
    public List<StatuteListBean> getMostStatute(Integer projectid,Integer isrelated){
        List<JudgementDto> judgementDtos = judgementRepository.findJudgementDto(projectid,isrelated);
        List<StatuteListBean> statuteListBeans = new ArrayList<>();
        for(JudgementDto j:judgementDtos)
        {
            StatuteListBean statuteListBean =new StatuteListBean(statuteRepository.findByStatuteid(j.getStatuteid())) ;
            statuteListBeans.add(statuteListBean);
        }
        return statuteListBeans;
    }

    @Override
    public List<FactListBean> getAllFromFactWhereStatuteId(String sid){
        List<Judgement> judgements=judgementRepository.findAllByStatuteIdAndIsrelated(sid,1);
        List<FactListBean> factListBeans=new ArrayList<>();
        for(int i =0;i<judgements.size();i++)
        {
            Judgement judgement = judgements.get(i);
            Fact fact = factRepository.findFirstByFactid(judgement.getFactId());
            factListBeans.add(new FactListBean(fact));
        }
        return factListBeans;
    }

    @Override
    public void downloadExportExcel(HttpServletResponse response, String statuteName, List<FactListBean> data) throws Exception {
        // 告诉浏览器用什么软件可以打开此文件
        response.setHeader("content-Type", "application/vnd.ms-excel");
        // 下载文件的默认名称
        String fileName = "《"+ statuteName +"》相关联事实"+".xlsx";
        response.setHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(fileName, "utf-8"));
        exportExcel(statuteName,data, response.getOutputStream());
    }

    private static void exportExcel(String sheetname,List<FactListBean> data, OutputStream out) throws Exception {

        XSSFWorkbook wb = new XSSFWorkbook();
        try {
            String sheetName = sheetname;
            if (null == sheetName) {
                sheetName = "Sheet1";
            }
            XSSFSheet sheet = wb.createSheet(sheetName);
            writeExcel( sheet, data);

            wb.write(out);
        } catch(Exception e){
            e.printStackTrace();
        }finally{
            //此处需要关闭 wb 变量
            out.close();
        }
    }

    private static void writeExcel( Sheet sheet, List<FactListBean> factListBeans) {
        // 产生表格标题行
        String headers[]={"事实内容","事实所属文书","文书中顺序"};

        Row row = sheet.createRow(0);
        for (short i = 0; i < headers.length; i++) {
            Cell cell = row.createCell(i);
            XSSFRichTextString text = new XSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }

        // 遍历集合数据，产生数据行
        Iterator<FactListBean> it = factListBeans.iterator();
        int index = 0;
        while (it.hasNext()) {
            index++;
            row = sheet.createRow(index);
            FactListBean factListBean = it.next();

            Cell cell1 = row.createCell(0);
            XSSFRichTextString id = new XSSFRichTextString(factListBean.getText()+"");
            cell1.setCellValue(id);
            Cell cell2 = row.createCell(1);
            XSSFRichTextString num = new XSSFRichTextString(factListBean.getInstrumentid()+"");
            cell2.setCellValue(num);
            Cell cell3 = row.createCell(2);
            XSSFRichTextString text = new XSSFRichTextString(factListBean.getNum()+"");
            cell3.setCellValue(text);
        }

    }










}
