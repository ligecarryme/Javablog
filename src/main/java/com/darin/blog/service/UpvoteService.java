package com.darin.blog.service;

import com.darin.blog.entity.Upvote;

public interface UpvoteService {
    Upvote getUpvote(Long id);
    Upvote updateUpvote(Upvote upvote);
}
