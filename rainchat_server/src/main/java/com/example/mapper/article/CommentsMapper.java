package com.example.mapper.article;

import com.example.domain.article.Comments;

import java.util.List;

public interface CommentsMapper {

    List<Comments> selectById(Comments comments);

    void insert(Comments comments);

    void delete(Comments comments);

    void updateByPrimaryKeySelective(Comments comments);
}
