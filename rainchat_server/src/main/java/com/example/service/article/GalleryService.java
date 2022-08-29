package com.example.service.article;

import com.example.domain.article.Gallery;

import java.util.List;

public interface GalleryService {
    List<Gallery> list(Gallery gallery);

    void delete(Gallery gallery);
    void updateById(Gallery gallery);

    void insert(Gallery gallery);

    void updateByUrl(Gallery gallery);

    void deleteByArticle(String articleId);

    List<Gallery> listArticle(Gallery gallery);
}
