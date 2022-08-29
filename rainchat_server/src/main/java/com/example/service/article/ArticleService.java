package com.example.service.article;

import com.example.domain.article.Article;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ArticleService {
    List<Article> list(Article article);

    void updateByPrimaryKeySelective(Article article);

    void uploadArticle(Article article);

    void deleteArticle(Article article);

    void updateArticle(Article article);
    Article getArticleById(Article article);

    void addViews(Article article);

    void renewArticle(Article article);

    List<Article> listByAuthorId(Article article);

    List<Article> listByStatus(Article article);

    List<Article> listOfCollect(Article article);

    List<Article> listOfRecommend();
}
