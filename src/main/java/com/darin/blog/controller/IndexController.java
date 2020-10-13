package com.darin.blog.controller;

import com.darin.blog.common.CommonResult;
import com.darin.blog.entity.Blog;
import com.darin.blog.entity.Tag;
import com.darin.blog.entity.Type;
import com.darin.blog.service.BlogService;
import com.darin.blog.service.TagService;
import com.darin.blog.service.TypeService;
import com.google.common.collect.ImmutableMap;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Controller
@Api(value = "indexController")
public class IndexController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @ApiOperation("主页博客列表")
    @GetMapping("/")
    @ResponseBody
    public CommonResult<Object> index(@RequestParam(value = "currentPage",defaultValue = "1") Integer currentPage){
        Pageable pageable = PageRequest.of(currentPage-1,6, Sort.Direction.ASC,"id");
        Page<Blog> page = blogService.listBlog(pageable);
        List<Type> typeList = typeService.listTypeTop(4);
        List<Tag> tagList = tagService.listTagTop(8);
        return CommonResult.success(ImmutableMap.of("blog",page.getContent(),"type",typeList,"tag",tagList) ,"获取成功");
    }

}
