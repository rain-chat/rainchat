package com.example.domain.article;

import com.alibaba.fastjson.annotation.JSONField;
import com.example.domain.extend.BasePageInfo;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Comments extends BasePageInfo {
    private Long id;
    private String parentId;
    private String text;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date date;
    private Long userId;
    private String userName;
    private int userLevel;
    private Long atId;
    private String atName;
    private int atLevel;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(int userLevel) {
        this.userLevel = userLevel;
    }

    public Long getAtId() {
        return atId;
    }

    public void setAtId(Long atId) {
        this.atId = atId;
    }

    public String getAtName() {
        return atName;
    }

    public void setAtName(String atName) {
        this.atName = atName;
    }

    public int getAtLevel() {
        return atLevel;
    }

    public void setAtLevel(int atLevel) {
        this.atLevel = atLevel;
    }
}
