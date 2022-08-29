package com.example.controller.article;

import com.alibaba.fastjson.JSON;
import com.example.domain.article.Comments;
import com.example.domain.extend.AjaxResult;
import com.example.service.article.CommentsService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentsController {
    @Resource
    private CommentsService commentsService;

    @RequestMapping("list")
    public String list(@RequestBody  Comments comments){
        List<Comments> commentsList = commentsService.selectById(comments);
        PageInfo<Comments> commentsPageInfo=new PageInfo<>(commentsList);
        return JSON.toJSONString(AjaxResult.success("查询成功",commentsPageInfo));
    }
    @RequestMapping("insert")
    public String insert(@RequestBody Comments comments){
        commentsService.insert(comments);
        return JSON.toJSONString(AjaxResult.success("插入成功"));
    }
}
