package com.example.service.article.Impl;

import com.example.domain.article.Article;
import com.example.domain.article.Gallery;
import com.example.mapper.article.GalleryMapper;
import com.example.service.article.GalleryService;
import com.example.service.tools.ToolService;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GalleryServiceImpl implements GalleryService {
    @Resource
    private GalleryMapper galleryMapper;
    @Resource
    private ToolService toolService;

    public List<Gallery> list(Gallery gallery){
        PageHelper.startPage(gallery.getPageNum(),gallery.getPageSize());
        return galleryMapper.list(gallery);
    }
    public List<Gallery> listArticle(Gallery  gallery){
        return galleryMapper.listArticle(gallery);
    }
    public void updateById(Gallery gallery){
        galleryMapper.updateByPrimaryKeySelective(gallery);
    }

    public void updateByUrl(Gallery gallery){
        galleryMapper.updateByUrl(gallery);
    }
    public void delete(Gallery gallery) {
        galleryMapper.delete(gallery);
    }

    public void deleteByArticle(String articleId){
        Gallery gallery=new Gallery();
        gallery.setArticleId(articleId);
        List<Gallery> list = listArticle(gallery);
        for(int i=0;i<list.size();i++){
            toolService.deleteFile(list.get(i).getUrl());
        }
        galleryMapper.deleteByArticle(gallery);
    }

    public void insert(Gallery gallery){
        gallery.setStatus("已启用");
        galleryMapper.insert(gallery);
    }
}
