package com.example.controller.article;

import com.alibaba.fastjson.JSON;
import com.example.domain.article.Article;
import com.example.domain.article.Gallery;
import com.example.domain.extend.AjaxResult;
import com.example.service.article.GalleryService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/gallery")
public class GalleryController {

    @Resource
    private GalleryService galleryService;

    @RequestMapping("/list")
    public String list(@RequestBody Gallery gallery) {
        List<Gallery> galleryList=galleryService.list(gallery);
        PageInfo<Gallery> galleryPageInfo=new PageInfo<>(galleryList);
        return JSON.toJSONString(AjaxResult.success("请求成功",galleryPageInfo));
    }

    @RequestMapping("/delete")
    public String delete(@RequestBody Gallery gallery) {
        galleryService.delete(gallery);
        return JSON.toJSONString(AjaxResult.success("删除成功"));
    }
    @RequestMapping("/update")
    public String update(@RequestBody Gallery gallery) {
        galleryService.updateById(gallery);
        return JSON.toJSONString(AjaxResult.success("修改成功"));
    }
}
