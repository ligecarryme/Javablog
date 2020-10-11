package com.darin.blog.service.Impl;

import com.darin.blog.NotFoundException;
import com.darin.blog.dao.BlogRepository;
import com.darin.blog.dto.SearchBlogParam;
import com.darin.blog.entity.Blog;
import com.darin.blog.entity.Type;
import com.darin.blog.service.BlogService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Transactional
    @Override
    public Blog getBlog(Long id) {
        return blogRepository.getOne(id);
    }

    @Transactional
    @Override
    public Page<Blog> listBlog(Pageable pageable) {
        return blogRepository.findAll(pageable);
    }

    @Transactional
    @Override
    public Page<Blog> listBlog(Pageable pageable, SearchBlogParam blog) {
        return blogRepository.findAll((Specification<Blog>) (root, cq, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (!"".equals(blog.getTitle().trim()) && blog.getTitle() != null){
                predicates.add(cb.like(root.<String>get("title"), "%"+blog.getTitle()+"%"));
            }
            if (blog.getTypeid() != null){
                predicates.add(cb.equal(root.<Type>get("type").get("id"),blog.getTypeid()));
            }
            // 推荐选项
            if (blog.isRecommend()){
                predicates.add(cb.equal(root.<Boolean>get("recommend"),blog.isRecommend()));
            }
            cq.where(predicates.toArray(new Predicate[predicates.size()]));
            return null;
        },pageable);
    }

    @Transactional
    @Override
    public Blog saveBlog(Blog blog) {
        blog.setCreateTime(new Date());
        blog.setUpdateTime(new Date());
        blog.setViews(0);
        return blogRepository.save(blog);
    }

    @Transactional
    @Override
    public Blog updateBlog(Long id, Blog blog) {
        Blog b = blogRepository.getOne(id);
        if (b == null){
            throw new NotFoundException("博客类型不存在");
        }

//        BeanUtils.copyProperties(blog,b);
        blog.setUpdateTime(new Date());
        blog.setCreateTime(b.getCreateTime());
        blog.setViews(b.getViews());
        return blogRepository.save(blog);
    }

    @Transactional
    @Override
    public void deleteBlog(Long id) {
        blogRepository.deleteById(id);
    }
}
