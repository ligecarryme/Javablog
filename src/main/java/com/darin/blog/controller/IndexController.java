package com.darin.blog.controller;

import com.darin.blog.common.CommonResult;
import com.darin.blog.dto.PageParam;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
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
    public CommonResult<Object> index(@RequestParam(value = "currentPage",defaultValue = "1") Integer currentPage){
        Pageable pageable = PageRequest.of(currentPage-1,6, Sort.Direction.ASC,"id");
        Page<Blog> page = blogService.listBlog(pageable);
        List<Type> typeList = typeService.listTypeTop(4);
        List<Tag> tagList = tagService.listTagTop(8);
        List<Blog> blogList = blogService.listBlogTop(3);
        PageParam pageParam = new PageParam(currentPage,page.getTotalPages(),(int) page.getTotalElements());
        return CommonResult.success(ImmutableMap.of("pageinfo",pageParam,"blog",page.getContent(),"topBlog",blogList,"type",typeList,"tag",tagList) ,"获取成功");
    }

    @ApiOperation("搜索博客")
    @PostMapping("/search")
    public CommonResult<Page> search(@RequestParam(value = "currentPage",defaultValue = "1") Integer currentPage, @RequestParam String query){
        Pageable pageable = PageRequest.of(currentPage-1,6, Sort.Direction.ASC,"id");
        Page<Blog> blogsearch = blogService.listBlog(pageable,"%"+query+"%");
        return CommonResult.success(blogsearch,"搜索成功");
    }

    @ApiOperation("博客详情")
    @GetMapping("/blogDetail/{id}")
    public CommonResult<Object> detail(@PathVariable Long id){
        Blog blog = blogService.getBlog(id);
        return CommonResult.success(blog,"博客详情获取成功");
    }


}
