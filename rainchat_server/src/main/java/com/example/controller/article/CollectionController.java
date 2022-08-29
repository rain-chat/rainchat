package com.example.controller.article;

import com.alibaba.fastjson.JSON;
import com.example.domain.article.Article;
import com.example.domain.article.Collection;
import com.example.domain.extend.AjaxResult;
import com.example.service.article.ArticleService;
import com.example.service.article.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/collection")
public class CollectionController {
    @Autowired
    private CollectionService collectionService;

    @Autowired
    private ArticleService articleService;

    @RequestMapping("/exist")
    public String exist(@RequestBody Collection collection){
        Collection coll=collectionService.getCollection(collection);
        if(coll==null) return JSON.toJSONString(AjaxResult.success("查询成功",false));
        else return JSON.toJSONString(AjaxResult.success("查询成功",true));
    }

    @RequestMapping("/collect")
    public String collect(@RequestBody Collection collection){
        collectionService.insert(collection);
        Article obj=new Article();
        obj.setId(collection.getArticleId());
        Article article = articleService.getArticleById(obj);
        article.setCollection(article.getCollection()+1);
        articleService.renewArticle(article);
        return JSON.toJSONString(AjaxResult.success("收藏成功"));
    }

    @RequestMapping("/desCollect")
    public String desCollect(@RequestBody Collection collection){
        Article obj=new Article();
        obj.setId(collection.getArticleId());
        Article article = articleService.getArticleById(obj);
        if(article!=null) {
            article.setCollection(article.getCollection() - 1);
            articleService.renewArticle(article);
        }
        collectionService.delete(collection);
        return JSON.toJSONString(AjaxResult.success("已取消收藏"));
    }
}
