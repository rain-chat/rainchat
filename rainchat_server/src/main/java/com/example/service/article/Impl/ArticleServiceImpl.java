package com.example.service.article.Impl;

import com.example.domain.article.Article;
import com.example.domain.article.Collection;
import com.example.domain.article.Gallery;
import com.example.domain.extend.TokenUtil;
import com.example.domain.user.User;
import com.example.mapper.article.ArticleMapper;
import com.example.service.article.ArticleService;
import com.example.service.article.CommentsService;
import com.example.service.article.GalleryService;
import com.example.service.tools.ToolService;
import com.example.service.user.UserService;
import com.github.pagehelper.PageHelper;
import com.mysql.cj.util.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Resource
    private ArticleMapper articleMapper;

    @Resource
    private TokenUtil tokenUtil;

    @Resource
    private GalleryService galleryService;

    @Resource
    private CommentsService commentsService;

    @Resource
    private UserService userService;

    @Resource
    private ToolService toolService;

    public List<Article> list(Article article){
        PageHelper.startPage(article.getPageNum(),article.getPageSize());
        return articleMapper.selectAll(article);
    }
    public List<Article> listByAuthorId(Article article){
        PageHelper.startPage(article.getPageNum(),article.getPageSize());
        return articleMapper.selectByAuthorId(article);
    }
    public List<Article> listByStatus(Article article){
        PageHelper.startPage(article.getPageNum(),article.getPageSize());
        return articleMapper.selectByStatus(article);
    }
    public void uploadArticle(Article article){
        //获取用户的id
        article.setCollection(0L);
        article.setViews(0L);
        article.setRecommend("否");
        article.setAuthorId(tokenUtil.getUserId());
        article.setUploadDate(new Date());
        article.setId(UUID.randomUUID().toString());
        List list=article.getGallery();
        if(list!=null) for (int i=0;i<list.size();i++){
            Gallery gallery=new Gallery();
            gallery.setArticleId(article.getId());
            gallery.setUrl((String)list.get(i));
            galleryService.insert(gallery);
        }
        userService.levelUp(tokenUtil.getUserId(),5);
        articleMapper.insert(article);
    }

    public void deleteArticle(Article article){
        // 清除云端图片
        galleryService.deleteByArticle(article.getId());
        // 清除文章评论
        commentsService.delete(article.getId());
        articleMapper.deleteById(article);
    }

    public void updateByPrimaryKeySelective(Article article){
        if(StringUtils.isNullOrEmpty(article.getImage())){
            article.setImage(null);
        } else {
            Article obj=new Article();
            obj.setId(article.getId());
            Article oldObj= articleMapper.selectById(obj);
            if(!oldObj.getImage().equals(article.getImage())){
                toolService.deleteFile(oldObj.getImage());
            }
        }
        articleMapper.updateByPrimaryKeySelective(article);
    }
    public void updateArticle(Article article){
        article.setUploadDate(new Date());
        List list=article.getGallery();
        if(list!=null&&list.size()>0) for (int i=0;i<list.size();i++){
            Gallery gallery=new Gallery();
            gallery.setArticleId(article.getId());
            gallery.setUrl((String)list.get(i));
            galleryService.insert(gallery);
        }
        updateByPrimaryKeySelective(article);
    }

//收藏文章不需要更新时间
    public void renewArticle(Article article){
        updateByPrimaryKeySelective(article);
    }

    public Article getArticleById(Article article){
        return articleMapper.selectById(article);
    }

    public void addViews(Article article){
        article.setViews(articleMapper.selectById(article).getViews()+1);
        renewArticle(article);
    }
    public List<Article> listOfCollect(Article article){
        Collection collection=new Collection();
        collection.setUserId(tokenUtil.getUserId().toString());
        article.setCollect(collection);
        return articleMapper.listOfCollect(article);
    }
    public List<Article> listOfRecommend(){
        return articleMapper.selectByRecommend();
    }
}
