package com.p14172wi.domain.post.postComment.repository;

import com.p14172wi.domain.post.postComment.entity.PostComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostCommentRepository extends JpaRepository<PostComment, Long> {

    List<PostComment> findAllByPostIdOrderByIdAsc(Long postId);

    Optional<PostComment> findByIdAndPostId(Long id, Long postId);
}
