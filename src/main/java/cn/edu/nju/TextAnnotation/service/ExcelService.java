package cn.edu.nju.TextAnnotation.service;



import cn.edu.nju.TextAnnotation.bean.FactListBean;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


public interface ExcelService {


  public String batchImport(String fileName, MultipartFile mfile);

  public List<FactListBean> getAllFromFactWhereStatuteId(String statuteid);

  public  void downloadExportExcel(HttpServletResponse response, String statuteName, List<FactListBean> data) throws Exception;


}
