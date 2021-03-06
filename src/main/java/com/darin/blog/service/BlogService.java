package com.darin.blog.service;

import com.darin.blog.dto.SearchBlogParam;
import com.darin.blog.entity.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface BlogService {

    Blog getBlog(Long id);

    Page<Blog> listBlog(Pageable pageable, SearchBlogParam blog);

    Page<Blog> listBlog(Pageable pageable);

    Page<Blog> listBlog(Pageable pageable, String query);

    Page<Blog> listBlogByTypeId(Pageable pageable, Long id);

    Page<Blog> listBlogByTagId(Pageable pageable, Long id);

    Map<String, List<Blog>> archiveBlog();

    Long countBlog();

    List<Blog> listBlogTop(Integer size);

    Blog saveBlog(Blog blog);

    Blog updateBlog(Long id, Blog blog);

    void deleteBlog(Long id);

}
