package cn.edu.nju.TextAnnotation.service;



import org.springframework.web.multipart.MultipartFile;



public interface ExcelService {


  public String batchImport(String fileName, MultipartFile mfile);


}
