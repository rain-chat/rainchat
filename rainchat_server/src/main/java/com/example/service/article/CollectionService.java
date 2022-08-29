package com.example.service.article;

import com.example.domain.article.Collection;

public interface CollectionService {

    Collection getCollection(Collection collection);

    void insert(Collection collection);

    void delete(Collection collection);

    void deleteByUser(Collection collection);
}
