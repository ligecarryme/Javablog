package com.darin.blog.service.Impl;

import com.darin.blog.dao.UpvoteRepository;
import com.darin.blog.entity.Upvote;
import com.darin.blog.service.UpvoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpvoteServiceImpl implements UpvoteService {

    @Autowired
    UpvoteRepository upvoteRepository;

    @Override
    public Upvote getUpvote(Long id) {
        return upvoteRepository.findById(id).get();
    }

    @Override
    public Upvote updateUpvote(Upvote upvote){
        return upvoteRepository.save(upvote);
    }
}
