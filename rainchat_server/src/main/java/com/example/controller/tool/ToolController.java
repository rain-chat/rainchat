package com.example.controller.tool;


import com.alibaba.fastjson.JSON;
import com.example.domain.extend.AjaxResult;
import com.example.service.tools.ToolService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tool")
public class ToolController {

    @Resource
    private ToolService toolService;

    @RequestMapping("/delete/{url}")
    public String deleteFileList(@PathVariable("url") String url){
        toolService.deleteFile(url);
        return JSON.toJSONString(AjaxResult.success("删除完成"));
    }


    // @RequestParam(value="image",required=false)
    //value的值应该和前端formData的键值对保持一致
    @RequestMapping("/uploadImages")
    public String uploadImage(@RequestParam(value="images",required=false) MultipartFile[] files) throws Exception{
        List list=new ArrayList();
        if(files!=null&&files.length>0){
           for(int i=0;i<files.length;i++){
               if(files[i]!=null){
                   String url=toolService.uploadImage(files[i]);
                   list.add(url);
               }
            }
        }
        return JSON.toJSONString(AjaxResult.success("上传成功",list));
    }
}
