package com.p14172wi.domain.post.postComment.controller;

import com.p14172wi.domain.post.postComment.dto.PostCommentRequest;
import com.p14172wi.domain.post.postComment.dto.PostCommentDto;
import com.p14172wi.domain.post.postComment.service.PostCommentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/posts/{postId}/comments")
public class PostCommentController {

    private final PostCommentService commentService;

    public PostCommentController(PostCommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public List<PostCommentDto> findAll(@PathVariable Long postId) {
        return commentService.findAllByPost(postId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PostCommentDto create(@PathVariable Long postId, @RequestBody PostCommentRequest request) {
        return commentService.create(postId, request);
    }

    @PutMapping("/{commentId}")
    public PostCommentDto update(@PathVariable Long postId,
                                  @PathVariable Long commentId,
                                  @RequestBody PostCommentRequest request) {
        return commentService.update(postId, commentId, request);
    }

    @DeleteMapping("/{commentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long postId, @PathVariable Long commentId) {
        commentService.delete(postId, commentId);
    }
}
