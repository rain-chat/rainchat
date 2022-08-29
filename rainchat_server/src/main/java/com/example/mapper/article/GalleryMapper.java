package com.example.mapper.article;

import com.example.domain.article.Article;
import com.example.domain.article.Gallery;

import java.util.List;

public interface GalleryMapper {
    List<Gallery> urlList();

    List<Gallery> list(Gallery gallery);


    void delete(Gallery gallery);

    void updateByPrimaryKeySelective(Gallery gallery);

    void insert(Gallery gallery);

    void updateByUrl(Gallery gallery);

    void deleteByArticle(Gallery gallery);

    List<Gallery> listArticle(Gallery gallery);
}
