package com.example.mapper.article;

import com.example.domain.article.Article;

import java.util.List;

public interface ArticleMapper {

    List<Article> selectAll(Article article);

    void insert(Article article);

    void deleteById(Article article);

    void updateByPrimaryKeySelective(Article article);

    Article selectById(Article article);
    List<Article> selectByStatus(Article article);
    List<Article> selectByAuthorId(Article article);
    List<Article> listOfCollect(Article article);
    List<Article> selectByRecommend();
}
