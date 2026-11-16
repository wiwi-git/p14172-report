package com.p14172wi.domain.post.postComment.controller;

import com.p14172wi.domain.post.postComment.dto.PostCommentRequest;
import com.p14172wi.domain.post.postComment.dto.PostCommentDto;
import com.p14172wi.domain.post.postComment.service.PostCommentService;
import com.p14172wi.global.rsData.RsData;
import org.springframework.http.HttpStatus;
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
@RequestMapping("/api/v1/posts/{postId}/comments")
public class PostCommentController {

    private final PostCommentService commentService;

    public PostCommentController(PostCommentService commentService) {
        this.commentService = commentService;
    }

    // GET /api/v1/posts/{postId}/comments
    @GetMapping
    public List<PostCommentDto> findAll(@PathVariable Long postId) {
        return commentService.findAllByPost(postId);
    }

    // GET /api/v1/posts/{postId}/comments/{id}
    @GetMapping("/{id}")
    public PostCommentDto findById(@PathVariable Long postId, @PathVariable Long id) {
        return commentService.findById(postId, id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RsData<PostCommentDto> create(@PathVariable Long postId, @RequestBody PostCommentRequest request) {
        PostCommentDto comment = commentService.create(postId, request);
        return new RsData<>("201-1", "%d번 댓글이 작성되었습니다.".formatted(comment.id()), comment);
    }

    @PutMapping("/{id}")
    public RsData<PostCommentDto> update(@PathVariable Long postId,
                                         @PathVariable Long id,
                                         @RequestBody PostCommentRequest request) {
        PostCommentDto comment = commentService.update(postId, id, request);
        return new RsData<>("200-1", "%d번 댓글이 수정되었습니다.".formatted(id), comment);
    }

    // GET /api/v1/posts/{postId}/comments/{id}/delete  (과제 요구사항: 삭제도 GET 으로 처리)
    @GetMapping("/{id}/delete")
    public RsData<Void> delete(@PathVariable Long postId, @PathVariable Long id) {
        commentService.delete(postId, id);
        return new RsData<>("200-1", "%d번 댓글이 삭제되었습니다.".formatted(id));
    }
}
