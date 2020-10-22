package com.darin.blog.controller;

import com.darin.blog.common.CommonResult;
import com.darin.blog.entity.Blog;
import com.darin.blog.service.BlogService;
import com.google.common.collect.ImmutableMap;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Api(value = "ArchiveShowController")
@RestController
public class ArchiveShowController {

    @Autowired
    BlogService blogService;

    @GetMapping("/archives")
    public CommonResult<Object> archives(){
        Map<String, List<Blog>> map = blogService.archiveBlog();
        Long count = blogService.countBlog();
        return CommonResult.success(ImmutableMap.of("archives",map,"count",count),"获取归档博客成功");
    }

}
