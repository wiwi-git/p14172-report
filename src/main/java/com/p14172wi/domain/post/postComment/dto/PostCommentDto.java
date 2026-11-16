package com.p14172wi.domain.post.postComment.dto;

import com.p14172wi.domain.post.postComment.entity.PostComment;

import java.time.LocalDateTime;

public record PostCommentDto(
        Long id,
        Long postId,
        String content,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static PostCommentDto from(PostComment comment) {
        return new PostCommentDto(
                comment.getId(),
                comment.getPost().getId(),
                comment.getContent(),
                comment.getCreatedAt(),
                comment.getUpdatedAt()
        );
    }
}
