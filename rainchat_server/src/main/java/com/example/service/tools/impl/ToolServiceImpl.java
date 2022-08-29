package com.example.service.tools.impl;

import com.example.service.tools.ToolService;
import com.mysql.cj.util.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.UUID;

@Service
public class ToolServiceImpl implements ToolService {

    private static final String File_Path_Head="D:\\.Studio\\Spring\\resources\\";

    public Boolean deleteFile(String path){
        File file=new File(File_Path_Head+path);
        if(file.exists()){
            file.delete();
        }
        return true;
    }

    public String uploadImage(MultipartFile file) throws IOException {
        String extensionName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String relativePath= UUID.randomUUID().toString() + extensionName;
        String absolutePath=File_Path_Head+relativePath;
        File imgFile = new File(absolutePath);
        file.transferTo(imgFile);
        return relativePath;
    }

    //加载base64图片
    public String loadImage(String url){
        if(StringUtils.isNullOrEmpty(url)) return "";
        byte[] b = new byte[0];
        File file = new File(url);
        try(FileInputStream fis = new FileInputStream(file)){
            b =  new byte[(int)file.length()];
            fis.read(b);
        }catch (Exception e){}
        return Base64.getEncoder().encodeToString(b);
    }
}
