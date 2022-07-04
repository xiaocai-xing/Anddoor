package com.wan.door.controller;

import com.wan.door.common.ResultEntity;
import org.apache.commons.io.FileUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;


/**
 * @author: create by xiaocai-xing
 * @TODO: todo
 * @description: com.wan.door.controller
 * @date:2022/6/29
 */

@RestController
@RequestMapping("/public/tool")
public class ToolController {

    @PostMapping("/uploadfile")
    @ResponseBody
    public ResultEntity Uploadfile(HttpServletRequest request) throws Exception {

        FileUploadUtil fileUploadUtil = new FileUploadUtil();
        ResultEntity result = new ResultEntity();
        BufferedOutputStream stream = null;
        MultipartHttpServletRequest params = ((MultipartHttpServletRequest) request);
        MultipartFile file = ((MultipartHttpServletRequest) request).getFile("file");
        if (file.isEmpty()) {
            throw new RuntimeException("文件为空，请重新上传！");
        }
        String tempFilePath = System.getProperty("java.io.tmpir")+file.getOriginalFilename();
        File file1 = new File(tempFilePath);
        FileUtils.copyInputStreamToFile(file.getInputStream(),file1);
        try{
            byte[] bytes = file.getBytes();
            stream = new BufferedOutputStream(new FileOutputStream(new File(file.getOriginalFilename())));
            stream.write(bytes);
            stream.close();
        }catch (Exception e){
            stream = null;
        }


        String remoteFileName = params.getParameter("fileName");
        String targetPath = params.getParameter("filePath");

        result = fileUploadUtil.uploadFile(file1,targetPath,remoteFileName);



        return result;
    }
}
