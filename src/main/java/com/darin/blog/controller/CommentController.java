package com.darin.blog.controller;

import com.darin.blog.common.CommonResult;
import com.darin.blog.dto.CommentParam;
import com.darin.blog.entity.Comment;
import com.darin.blog.service.BlogService;
import com.darin.blog.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(value = "CommentController")
public class CommentController {

    @Autowired
    CommentService commentService;

    @Autowired
    BlogService blogService;

    @ApiOperation("获取评论列表")
    @GetMapping("/comments/{blogId}")
    public CommonResult<Object> comments(@PathVariable Long blogId){
        List<Comment> commentList = commentService.listCommentByBlogId(blogId);
        return CommonResult.success(commentList,"评论列表获取成功");
    }

    @ApiOperation("保存评论")
    @PostMapping("/comments")
    public CommonResult<Object> post(@RequestBody CommentParam commentParam){
        Comment comment = new Comment();
        BeanUtils.copyProperties(commentParam,comment);
//        Long blogId = comment.getBlog().getId();
        comment.setBlog(blogService.getBlog(commentParam.getBlogId()));
        commentService.saveComment(comment);
        return CommonResult.success(null,"保存成功");
    }



}
