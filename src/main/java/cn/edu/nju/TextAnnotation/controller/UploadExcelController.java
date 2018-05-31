package cn.edu.nju.TextAnnotation.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpSession;


import cn.edu.nju.TextAnnotation.service.ExcelService;
import cn.edu.nju.TextAnnotation.util.ImportExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
@RequestMapping("/import")
public class UploadExcelController {
    @Autowired
    ExcelService excelService;

    @RequestMapping(value = "",method = RequestMethod.GET)
    public String showImport(){
        return "/excel/import";
    }

    @PostMapping(value = "/upload")
    public  String  uploadExcel(HttpServletRequest request,HttpSession session)  {
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("filename");
        MultipartFile file = null;
        String message = "";
        for (int i = 0; i < files.size(); ++i) {
            file = files.get(i);
            //判断文件是否为空
            if (file == null) {
                session.setAttribute("msg", "文件不能为空！");
            }
                //获取文件名
                String fileName = file.getOriginalFilename();
                //验证文件名是否合格
                if (!ImportExcelUtil.validateExcel(fileName)) {
                    session.setAttribute("msg", "文件必须是excel格式！");
                    return "/excel/import";
                }
                    //批量导入
                    message += excelService.batchImport(fileName, file);
                    message += "——————";
                    session.setAttribute("msg", message);
            }
        return "/excel/import";
    }

}
