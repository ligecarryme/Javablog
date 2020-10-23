package com.darin.blog.service.Impl;

import com.darin.blog.dao.CommentRepository;
import com.darin.blog.entity.Comment;
import com.darin.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public List<Comment> listCommentByBlogId(Long blogId) {
        Sort sort = Sort.by(Sort.Direction.DESC,"createTime");
        return commentRepository.findByBlogId(blogId,sort);
    }

    @Override
    public Comment saveComment(Comment comment) {
        comment.setCreateTime(new Date());
        return commentRepository.save(comment);
    }
}
