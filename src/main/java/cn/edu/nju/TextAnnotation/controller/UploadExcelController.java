package cn.edu.nju.TextAnnotation.controller;

import java.util.List;


import cn.edu.nju.TextAnnotation.service.ExcelService;
import cn.edu.nju.TextAnnotation.util.ImportExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/import")
public class UploadExcelController {
    @Autowired
    ExcelService excelService;

    @RequestMapping(value = "",method = RequestMethod.GET)
    public String showImport(){
        return "/manager/import";
    }

//    @PostMapping(value = "/upload.action")
//    public  String  uploadExcel(HttpServletRequest request, HttpSession session)  {
//        MultipartFile file = null;
//        String message = "";
//        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("filename");
//
//
//                        //批量导入
//                        message += excelService.batchImport(files);
//
//                        session.setAttribute("msg", message);
//
//
//        return "/excel/import";
//    }


@PostMapping(value = "/upload.action")
public @ResponseBody
String uploadExcel(@RequestParam("filename") List<MultipartFile> files)  {
    MultipartFile file = null;
    String message = "";
    message = excelService.batchImport(files);
//    for (int i = 0; i < files.size(); ++i) {
//        file = files.get(i);
//        //判断文件是否为空
//        if (file == null) {
//            message += "文件不能为空！";
//        } else {
//            //获取文件名
//            String fileName = file.getOriginalFilename();
//            //验证文件名是否合格
//            if (!ImportExcelUtil.validateExcel(fileName)) {
//                message += "文件必须是excel格式！";
//            } else {
//                //批量导入
//                message += excelService.batchImport(fileName, file);
//                message += ",第"+ (i+1) +"个文件存储完毕!<br/>";
//            }
//        }
//    }
    return message;

}

}
