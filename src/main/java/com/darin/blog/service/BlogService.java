package com.darin.blog.service;

import com.darin.blog.dto.SearchBlogParam;
import com.darin.blog.entity.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BlogService {

    Blog getBlog(Long id);

    Page<Blog> listBlog(Pageable pageable, SearchBlogParam blog);

    Page<Blog> listBlog(Pageable pageable);

    List<Blog> listBlogTop(Integer size);

    Blog saveBlog(Blog blog);

    Blog updateBlog(Long id, Blog blog);

    void deleteBlog(Long id);

}
