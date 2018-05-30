package cn.edu.nju.TextAnnotation.service.serviceImpl;

import cn.edu.nju.TextAnnotation.model.*;
import cn.edu.nju.TextAnnotation.repository.*;
import cn.edu.nju.TextAnnotation.service.ExcelService;
import cn.edu.nju.TextAnnotation.util.ImportExcelUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ClassUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


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

    /**
     * 上传excel文件到临时目录后并开始解析
     * @param fileName
     * @param mfile
     * @return
     */
    @Override
    public String batchImport(String fileName, MultipartFile mfile){
        String path= ClassUtils.getDefaultClassLoader().getResource("").getPath();//获取项目绝对路径

        File uploadDir = new  File(path);
        //创建一个目录 （它的路径名由当前 File 对象指定，包括任一必须的父路径。）
        if (!uploadDir.exists()) uploadDir.mkdirs();
        //新建一个文件
        File tempFile = new File(path + fileName );
        //初始化输入流
        InputStream is = null;
        try{
            //将上传的文件写入新建的文件中
            mfile.transferTo(tempFile);

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
            return readExcelValue(wb,fileName,tempFile);
        }catch(Exception e){
            e.printStackTrace();
            return "导入出错！请检查数据格式！";
        } finally{
            if(is !=null)
            {
                try{
                    is.close();
                }catch(IOException e){
                    is = null;
                    e.printStackTrace();
                    return "导入出错！请检查数据格式！";
                }
            }
        }

    }

    private Integer saveStatuteWithLaw1Article(Statute statute,Integer count){
        String name = statute.getName();
        String docName = name.substring(0,name.indexOf("("));
        String articleSeq = name.substring(name.indexOf(")")+1,name.length());
        Law1Article law1Article = law1ArticleRepository.findFirstByArticleSeqAndDocName(articleSeq,docName);
        String id = law1Article.getCode();
        statute.setStatuteid(id);//从law1Article表中取到Statuteid
        if(statuteRepository.findByStatuteid(id) == null)//判断法条表中是否已存在该条，若不存在，保存
        { statuteRepository.save(statute);
            count += 1;
        }
        return count;
    }


    private Integer saveInstrument(Instrument instrument,Integer count){
        if(instrumentRepository.findFirstByInstrumentid(instrument.getInstrumentid())==null)
        {
            instrumentRepository.save(instrument);
            count += 1;
        }
        return count;
    }


    private Integer saveInstrumentAndStatute(InstrumentAndStatute instrumentAndStatute,String statute_name,Integer count){
        Statute statute =  statuteRepository.findFirstByName(statute_name);
        instrumentAndStatute.setStatuteid(statute.getStatuteid());
        if(instrumentAndStatueRepository.findFirstByInstrumentidAndStatuteid(instrumentAndStatute.getInstrumentid(),instrumentAndStatute.getStatuteid())==null)
        {
            instrumentAndStatueRepository.save(instrumentAndStatute);
            count += 1;
        }
        return count;
    }


    private Integer saveFact(Fact fact,Integer count)
    {
        if(factRepository.findFirstByInstrumentidAndNum(fact.getInstrumentid(),fact.getNum())==null)
        {
            factRepository.save(fact);
            count += 1;
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
    /**
     * 解析Excel里面的数据
     * @param wb
     * @return
     */
    private String readExcelValue(Workbook wb,String fileName,File tempFile){



        //错误信息接收器
        String errorMsg = "";

        //成功条数计数
        int count=0;

        //存文书表
        Instrument instrument = readInstrument(fileName);
        count = saveInstrument(instrument,count);
        errorMsg = "存instrument表"+ count +"条;";

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
                            Statute statute = new Statute();
                            Cell text = sheet.getRow(0).getCell(colNum);
                            String name = getStatute_name(text.getStringCellValue());
                            statute.setName(name);//获得法条名称
                            statute.setText(text.getStringCellValue());//获得法条内容
                            //存法条表
                            count = saveStatuteWithLaw1Article(statute,count);

                        }
                    }
                    errorMsg += "存Statute表"+count+"条;";

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
                    errorMsg += "存instrumentAndStatute表"+count+"条;";

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
                    errorMsg += "存Fact表"+count+"条";
                }
            }


        //删除上传的临时文件
        if(tempFile.exists()){
            tempFile.delete();
        }

        return errorMsg;
    }
}
