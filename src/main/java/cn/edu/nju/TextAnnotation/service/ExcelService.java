package cn.edu.nju.TextAnnotation.service;



import cn.edu.nju.TextAnnotation.bean.FactListBean;
import cn.edu.nju.TextAnnotation.bean.ResultMessageBean;
import cn.edu.nju.TextAnnotation.bean.StatuteListBean;
import cn.edu.nju.TextAnnotation.model.Fact;
import cn.edu.nju.TextAnnotation.model.Instrument;
import cn.edu.nju.TextAnnotation.model.InstrumentAndStatute;
import cn.edu.nju.TextAnnotation.model.Statute;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;


public interface ExcelService {


  public String batchImport(List<MultipartFile> files);

  public List<FactListBean> getAllFromFactWhereStatuteId(String statuteid);

  public  void downloadExportExcel(HttpServletResponse response, String statuteName, List<FactListBean> data) throws Exception;

  public List<StatuteListBean> getMostStatute(Integer projectid,Integer isrelated);

  public String saveIntoInstrumentAndStatute(Workbook wb,String fileName);
  public String saveIntoInstrumentAndStatuteAndFact(Workbook wb, String fileName);
  public Integer saveFact(Fact fact, Integer count);
  public Integer saveInstrumentAndStatute(InstrumentAndStatute instrumentAndStatute, String statute_name, Integer count);
  public Integer saveInstrument(Instrument instrument, Integer count);
  public Integer saveStatuteWithLaw1Article(Statute statute, Integer count);
}
