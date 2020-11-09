package com.darin.blog.controller;

import com.darin.blog.common.CommonResult;
import com.darin.blog.entity.Upvote;
import com.darin.blog.service.UpvoteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "UpvoteController")
public class UpvoteController {

    @Autowired
    UpvoteService upvoteService;

    @GetMapping("/upvote/get")
    @ApiOperation("获取点赞数")
    public CommonResult<Object> upvote(@RequestParam(required = false,defaultValue = "1") Long id){
        Upvote upvotes = upvoteService.getUpvote(id);
        return CommonResult.success(upvotes);
    }

    @GetMapping("/upvote/update")
    @ApiOperation("更新点赞数")
    public CommonResult<Object> update(@RequestParam Long votes){
        Upvote upvote = new Upvote((long) 1,votes);
        upvoteService.updateUpvote(upvote);
        return CommonResult.success(null,"更新成功");
    }
}
