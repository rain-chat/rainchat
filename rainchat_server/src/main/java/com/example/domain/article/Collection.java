package com.example.domain.article;

import com.alibaba.fastjson.annotation.JSONField;
import com.example.domain.extend.BasePageInfo;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Collection extends BasePageInfo {
    private String id;

    private  String userId;

    private  String articleId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date date;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
