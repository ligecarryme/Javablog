package com.darin.blog.controller.admin;

import com.darin.blog.common.CommonResult;
import com.darin.blog.dto.TagAndTypeParam;
import com.darin.blog.entity.Type;
import com.darin.blog.service.TypeService;
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
@Api(value = "TypeController")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @ApiOperation("获取分类列表")
    @GetMapping("/typeslist")
    @ResponseBody
    public CommonResult<Object> types(@RequestParam Integer currentPageNum){
        Pageable pageable = PageRequest.of(currentPageNum-1,8,Sort.Direction.ASC,"id");
        Page<Type> typePage = typeService.listType(pageable);
        return CommonResult.success(typePage,"获取列表成功");
    }

    @ApiOperation("分类新增和修改")
    @PostMapping("/addtypes")
    @ResponseBody
    public CommonResult<Object> post(@RequestBody TagAndTypeParam typeParam, Type type){
        Long id = typeParam.getId();
        String name = typeParam.getName();
        Type t = typeService.getTypeByname(name);
        if (t == null){
            type.setId(id);
            type.setName(name);
            typeService.saveType(type);
            return CommonResult.success(null,"新增成功");
        } else {
            return CommonResult.failed("类名重复");
        }
    }

    @ApiOperation("删除分类")
    @GetMapping("/deletetype/{id}")
    @ResponseBody
    public CommonResult<Object> delete(@PathVariable("id") Long id){
        typeService.deletType(id);
        return CommonResult.success(null,"删除成功");
    }
}
