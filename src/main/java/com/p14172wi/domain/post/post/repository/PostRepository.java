package com.p14172wi.domain.post.post.repository;

import com.p14172wi.domain.post.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
