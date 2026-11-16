package com.p14172wi.domain.post.post.controller;

import com.p14172wi.domain.post.post.dto.PostRequest;
import com.p14172wi.domain.post.post.dto.PostDto;
import com.p14172wi.domain.post.post.service.PostService;
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
@RequestMapping("/api/v1/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    // GET /api/v1/posts
    @GetMapping
    public List<PostDto> findAll() {
        return postService.findAll();
    }

    // GET /api/v1/posts/{id}
    @GetMapping("/{id}")
    public PostDto findById(@PathVariable Long id) {
        return postService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RsData<PostDto> create(@RequestBody PostRequest request) {
        PostDto post = postService.create(request);
        return new RsData<>("201-1", "%d번 글이 작성되었습니다.".formatted(post.id()), post);
    }

    @PutMapping("/{id}")
    public RsData<PostDto> update(@PathVariable Long id, @RequestBody PostRequest request) {
        PostDto post = postService.update(id, request);
        return new RsData<>("200-1", "%d번 글이 수정되었습니다.".formatted(id), post);
    }

    // GET /api/v1/posts/{id}/delete  (과제 요구사항: 삭제도 GET 으로 처리)
    @GetMapping("/{id}/delete")
    public RsData<Void> delete(@PathVariable Long id) {
        postService.delete(id);
        return new RsData<>("200-1", "%d번 글이 삭제되었습니다.".formatted(id));
    }
}
