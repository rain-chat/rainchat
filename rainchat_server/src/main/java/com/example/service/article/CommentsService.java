package com.example.service.article;

import com.example.domain.article.Comments;

import java.util.List;

public interface CommentsService {
    List<Comments> selectById(Comments comments);

    void insert(Comments comments);

    void delete(String articleId);

    List<Comments> selectByArticleId(Comments comments);

    void updateByPrimaryKeySelective(Comments comments);
}
