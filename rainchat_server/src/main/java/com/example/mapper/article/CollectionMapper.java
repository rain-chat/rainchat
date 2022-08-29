package com.example.mapper.article;

import com.example.domain.article.Collection;

public interface CollectionMapper {
    Collection selectByBothId(Collection collection);

    void insert(Collection collection);

    void delete(Collection collection);

    void deleteByUser(Collection collection);
}
