package com.example.service.article.Impl;

import com.example.domain.article.Article;
import com.example.domain.article.Comments;
import com.example.domain.extend.TokenUtil;
import com.example.domain.user.User;
import com.example.mapper.article.ArticleMapper;
import com.example.mapper.article.CommentsMapper;
import com.example.service.article.ArticleService;
import com.example.service.article.CommentsService;
import com.example.service.user.UserService;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class CommentsServiceImpl implements CommentsService {

    @Resource
    private CommentsMapper commentsMapper;
    @Resource
    private UserService userService;
    @Resource
    private ArticleMapper articleMapper;
    @Resource
    private TokenUtil tokenUtil;
    public List<Comments> selectById(Comments comments){
        PageHelper.startPage(comments.getPageNum(),comments.getPageSize());
        return commentsMapper.selectById(comments);
    }
    public List<Comments> selectByArticleId(Comments comments){
        return commentsMapper.selectById(comments);
    }
    public void insert(Comments comments){
        comments.setDate(new Date());
        comments.setUserId(tokenUtil.getUserId());
        Article article=new Article();
        article.setId(comments.getParentId());
        Article article1=articleMapper.selectById(article);
        if(article1!=null) userService.levelUp(article1.getAuthorId(),1);
        commentsMapper.insert(comments);
    }

    public void delete(String parentId){
        Comments comments=new Comments();
        comments.setParentId(parentId);
        List<Comments> commentsList = selectByArticleId(comments);
        for(int i=0;i<commentsList.size();i++){
            Comments com=new Comments();
            com.setParentId(commentsList.get(i).getId().toString());
            commentsMapper.delete(com);
            commentsMapper.delete(commentsList.get(i));
        }
    }
    public void updateByPrimaryKeySelective(Comments comments){
        commentsMapper.updateByPrimaryKeySelective(comments);
    }
}
