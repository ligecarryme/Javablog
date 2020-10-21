package com.darin.blog.controller;

import com.darin.blog.common.CommonResult;
import com.darin.blog.entity.Blog;
import com.darin.blog.entity.Tag;
import com.darin.blog.entity.Type;
import com.darin.blog.service.BlogService;
import com.darin.blog.service.TagService;
import com.google.common.collect.ImmutableMap;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(value = "TagShowController")
public class TagShowController {

    @Autowired
    private TagService tagService;

    @Autowired
    private BlogService blogService;


    @ApiOperation("根据标签获取博客")
    @GetMapping("/tags/{id}")
    public CommonResult<Object> tags(@PathVariable Long id, @RequestParam(defaultValue = "1") Integer current){
        List<Tag> tags = tagService.listTagTop(100);
        if (id == -1){
            id = tags.get(0).getId();
        }
        Pageable pageable = PageRequest.of(current-1,6, Sort.Direction.ASC,"updateTime");
        Page<Blog> page = blogService.listBlogByTagId(pageable,id);
        return CommonResult.success(ImmutableMap.of("tags",tags,"blogs",page));
    }


}
