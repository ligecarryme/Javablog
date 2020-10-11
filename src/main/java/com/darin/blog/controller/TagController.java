package com.darin.blog.controller;

import com.darin.blog.common.CommonResult;
import com.darin.blog.dto.TagAndTypeParam;
import com.darin.blog.entity.Tag;
import com.darin.blog.service.TagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
@Api(value = "TagController")
public class TagController {

    @Autowired
    private TagService tagService;

    @ApiOperation("标签列表")
    @GetMapping("/tagslist")
    @ResponseBody
    public CommonResult<Object> tags(@RequestParam Integer currentPageNum){
        Pageable pageable = PageRequest.of(currentPageNum-1,8, Sort.Direction.ASC,"id");
        Page<Tag> tagPage = tagService.listTag(pageable);
        return CommonResult.success(tagPage,"获取标签列表成功");
    }

    @ApiOperation("新增标签")
    @PostMapping("/addtag")
    @ResponseBody
    public CommonResult<Object> addPost(@RequestBody TagAndTypeParam tagParam, Tag tag){
        Long id = tagParam.getId();
        String name = tagParam.getName();
        Tag t = tagService.getTagByName(name);
        if (t == null){
            tag.setId(id);
            tag.setName(name);
            tagService.saveTag(tag);
            return CommonResult.success(null,"新增成功");
        }else {
            return CommonResult.failed("类名重复");
        }
    }

    @ApiOperation("删除标签")
    @GetMapping("/deletetag/{id}")
    @ResponseBody
    public CommonResult<Object> deleteTag(@PathVariable("id") Long id){
        tagService.deleteTag(id);
        return CommonResult.success(null,"删除成功");
    }

}
