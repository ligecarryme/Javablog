package com.darin.blog.controller.admin;

import com.darin.blog.common.CommonResult;
import com.darin.blog.dto.BlogParam;
import com.darin.blog.dto.SearchBlogParam;
import com.darin.blog.entity.Blog;
import com.darin.blog.entity.Tag;
import com.darin.blog.entity.Type;
import com.darin.blog.entity.User;
import com.darin.blog.service.BlogService;
import com.darin.blog.service.TagService;
import com.darin.blog.service.TypeService;
import com.darin.blog.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/admin")
@Api(value = "BlogController")
public class BlogController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;
    @Autowired
    private UserService userService;

    @ApiOperation("获取博客列表")
    @GetMapping("/bloglist")
    @ResponseBody
    public CommonResult<Object> bloglist(@RequestParam(value = "currentPage",required = false,defaultValue = "1") Integer currentPage){
        Pageable pageable = PageRequest.of(currentPage-1,8, Sort.Direction.ASC,"id");
        Page<Blog> blogs = blogService.listBlog(pageable);
        return CommonResult.success(blogs,"获取博客列表成功");
    }

    @ApiOperation("搜索博客")
    @GetMapping("/bloglist/search")
    @ResponseBody
    public CommonResult<Object> blogSearch(@RequestParam(value = "currentPage",required = false,defaultValue = "1") Integer currentPage, SearchBlogParam blog){
        Pageable pageable = PageRequest.of(currentPage-1,8, Sort.Direction.ASC,"id");
        Page<Blog> blogs = blogService.listBlog(pageable,blog);
        return CommonResult.success(blogs,"搜索博客成功");
    }

    @ApiOperation("获取博客标签")
    @GetMapping("/getTags")
    @ResponseBody
    public CommonResult<Object> getTags(){
        List<Tag> tagList = tagService.listTag();
        return CommonResult.success(tagList);
    }

    @ApiOperation("获取博客类型")
    @GetMapping("/getTypes")
    @ResponseBody
    public CommonResult<Object> getTypes(){
        List<Type> typeList = typeService.listType();
        return CommonResult.success(typeList);
    }

    @ApiOperation("新增博客")
    @PostMapping("/addblog")
    @ResponseBody
    public CommonResult<Object> post(@RequestBody BlogParam blogParam){
        Blog blog = new Blog();

        BeanUtils.copyProperties(blogParam,blog);
        blog.setUser(userService.getUser(blogParam.getUsername()));
        blog.setType(typeService.getType(blogParam.getTypeid()));
        blog.setTags(tagService.listTag(blogParam.getTagsid()));

        if (blog.getId() == 0){
            blogService.saveBlog(blog);
            return CommonResult.success(null,"新增成功");
        } else {
            blogService.updateBlog(blog.getId(),blog);
            return CommonResult.success(null,"修改成功");
        }
    }

    @ApiOperation("删除功能")
    @GetMapping("/bloglist/delete/{id}")
    @ResponseBody
    public CommonResult<Object> delete(@PathVariable Long id){
        blogService.deleteBlog(id);
        return CommonResult.success(null,"删除成功");
    }

}
