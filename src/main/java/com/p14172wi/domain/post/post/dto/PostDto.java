package com.p14172wi.domain.post.post.dto;

import com.p14172wi.domain.post.post.entity.Post;

import java.time.LocalDateTime;

public record PostDto(
        Long id,
        String title,
        String content,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static PostDto from(Post post) {
        return new PostDto(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getCreatedAt(),
                post.getUpdatedAt()
        );
    }
}
