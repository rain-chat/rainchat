package com.example.service.article.Impl;

import com.example.domain.article.Collection;
import com.example.domain.extend.TokenUtil;
import com.example.mapper.article.CollectionMapper;
import com.example.service.article.CollectionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;

@Service
public class CollectionServiceImpl implements CollectionService {

    @Resource
    private CollectionMapper collectionMapper;

    @Resource
    private TokenUtil tokenUtil;
    public Collection getCollection(Collection collection){
        collection.setUserId(tokenUtil.getUserId().toString());
        return collectionMapper.selectByBothId(collection);
    }

    public void insert(Collection collection){
        collection.setDate(new Date());
        collection.setId(UUID.randomUUID().toString());
        collection.setUserId(tokenUtil.getUserId().toString());
        collectionMapper.insert(collection);
    }

    public void delete(Collection collection){
        collection.setUserId(tokenUtil.getUserId().toString());
        collectionMapper.delete(collection);
    }
    public void deleteByUser(Collection collection){
        collectionMapper.deleteByUser(collection);
    }
}
