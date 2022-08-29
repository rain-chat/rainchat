package com.example.controller.article;

import com.alibaba.fastjson.JSON;
import com.example.domain.extend.AjaxResult;
import com.example.domain.article.Article;
import com.example.domain.extend.TokenUtil;
import com.example.service.article.ArticleService;
import com.example.service.tools.ToolService;
import com.github.pagehelper.PageInfo;
import com.mysql.cj.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;
    @Autowired
    private TokenUtil tokenUtil;
    @Autowired
    private ToolService toolService;

    @RequestMapping("/list")
    public String list(@RequestBody Article article){
        List<Article> articleList=articleService.list(article);
        PageInfo<Article> articlePageInfo=new PageInfo<>(articleList);
        return JSON.toJSONString(AjaxResult.success("查询成功",articlePageInfo));
    }
    @RequestMapping("/publicList")
    public String publicList(@RequestBody Article article){
        List<Article> articleList=articleService.listByStatus(article);
        PageInfo<Article> articlePageInfo=new PageInfo<>(articleList);
        return JSON.toJSONString(AjaxResult.success("查询成功",articlePageInfo));
    }
    @RequestMapping("/myCollect")
    public String myCollect(@RequestBody Article article){
        List<Article> articleList=articleService.listOfCollect(article);
        PageInfo<Article> articlePageInfo=new PageInfo<>(articleList);
        return JSON.toJSONString(AjaxResult.success("查询成功",articlePageInfo));
    }
    @RequestMapping("/ofAuthor")
    public String ofAuthor(@RequestBody Article article){
        List<Article> articleList=articleService.listByAuthorId(article);
        PageInfo<Article> articlePageInfo=new PageInfo<>(articleList);
        return JSON.toJSONString(AjaxResult.success("查询成功",articlePageInfo));
    }
    @RequestMapping("/myList")
    public String myList(@RequestBody Article article){
        article.setAuthorId(tokenUtil.getUserId());
        List<Article> articleList=articleService.listByAuthorId(article);
        PageInfo<Article> articlePageInfo=new PageInfo<>(articleList);
        return JSON.toJSONString(AjaxResult.success("查询成功",articlePageInfo));
    }

    @RequestMapping("/view")
    public String view(@RequestBody Article article){
        return JSON.toJSONString(AjaxResult.success("查询成功",articleService.getArticleById(article)));
    }
    @RequestMapping("/uploadArticle")
    public String uploadArticle(@RequestBody Article article) {
        articleService.uploadArticle(article);
        return JSON.toJSONString(AjaxResult.success("上传成功"));
    }

    @RequestMapping("/deleteArticle")
    public String deleteArticle(@RequestBody Article article) {
        articleService.deleteArticle(article);
        if(!StringUtils.isNullOrEmpty(article.getImage())){
            toolService.deleteFile(article.getImage());
        }
        return JSON.toJSONString(AjaxResult.success("删除成功"));
    }

    @RequestMapping("/updateArticle")
    public String updateArticle(@RequestBody Article article) {
        articleService.updateArticle(article);
        return JSON.toJSONString(AjaxResult.success("修改成功"));
    }

    @RequestMapping("/addViews")
    public String addViews(@RequestBody Article article) {
        articleService.addViews(article);
        return JSON.toJSONString(AjaxResult.success("修改成功"));
    }

    @RequestMapping("/recommend")
    public String recommend(@RequestBody Article article) {
        articleService.renewArticle(article);
        return JSON.toJSONString(AjaxResult.success("修改成功"));
    }

    @RequestMapping("/listRecommend")
    public String listRecommend() {
        List<Article> articleList=articleService.listOfRecommend();
        return JSON.toJSONString(AjaxResult.success("查询成功",articleList));
    }
}
