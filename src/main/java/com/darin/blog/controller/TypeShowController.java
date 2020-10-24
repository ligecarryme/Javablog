package com.darin.blog.controller;

import com.alibaba.fastjson.JSONObject;
import com.darin.blog.common.CommonResult;
import com.darin.blog.entity.Blog;
import com.darin.blog.entity.Type;
import com.darin.blog.service.BlogService;
import com.darin.blog.service.TypeService;
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
import java.util.Map;

@RestController
@Api(value = "TypeShowController")
public class TypeShowController {

    @Autowired
    private TypeService typeService;

    @Autowired
    private BlogService blogService;

    @ApiOperation("根据类型获取博客")
    @GetMapping("/types/{id}")
    public CommonResult<Object> types(@PathVariable Long id, @RequestParam(defaultValue = "1") Integer current){
        List<Type> types = typeService.listTypeTop(100);
        if (id == -1){
            id = types.get(0).getId();
        }
        Pageable pageable = PageRequest.of(current-1,6, Sort.Direction.ASC,"updateTime");
        Page<Blog> page = blogService.listBlogByTypeId(pageable,id);
//        String  jsonObject = JSONObject.toJSONString(page);
//        System.out.println(jsonObject);
        return CommonResult.success(ImmutableMap.of("types",types,"blogs",page));
    }

}
