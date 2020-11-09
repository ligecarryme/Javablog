package com.darin.blog.dao;

import com.darin.blog.entity.Upvote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UpvoteRepository extends JpaRepository<Upvote,Long> {
}
