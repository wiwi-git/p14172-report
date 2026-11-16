package com.p14172wi.domain.post.post.controller;

import com.p14172wi.domain.post.post.dto.PostRequest;
import com.p14172wi.domain.post.post.dto.PostDto;
import com.p14172wi.domain.post.post.service.PostService;
import com.p14172wi.global.rsData.RsData;
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
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public List<PostDto> findAll() {
        return postService.findAll();
    }

    @GetMapping("/{postId}")
    public PostDto findById(@PathVariable Long postId) {
        return postService.findById(postId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RsData<PostDto> create(@RequestBody PostRequest request) {
        PostDto post = postService.create(request);
        return new RsData<>("201-1", "%d번 글이 작성되었습니다.".formatted(post.id()), post);
    }

    @PutMapping("/{postId}")
    public RsData<PostDto> update(@PathVariable Long postId, @RequestBody PostRequest request) {
        PostDto post = postService.update(postId, request);
        return new RsData<>("200-1", "%d번 글이 수정되었습니다.".formatted(postId), post);
    }

    @DeleteMapping("/{postId}")
    public RsData<Void> delete(@PathVariable Long postId) {
        postService.delete(postId);
        return new RsData<>("200-1", "%d번 글이 삭제되었습니다.".formatted(postId));
    }
}
