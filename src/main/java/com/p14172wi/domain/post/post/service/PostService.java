package com.p14172wi.domain.post.post.service;

import com.p14172wi.domain.post.post.entity.Post;
import com.p14172wi.domain.post.post.dto.PostRequest;
import com.p14172wi.domain.post.post.dto.PostDto;
import com.p14172wi.domain.post.post.repository.PostRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<PostDto> findAll() {
        return postRepository.findAll().stream()
                .map(PostDto::from)
                .toList();
    }

    public PostDto findById(Long id) {
        return PostDto.from(getPost(id));
    }

    @Transactional
    public PostDto create(PostRequest request) {
        Post post = postRepository.save(new Post(request.title(), request.content()));
        return PostDto.from(post);
    }

    @Transactional
    public PostDto update(Long id, PostRequest request) {
        Post post = getPost(id);
        post.update(request.title(), request.content());
        postRepository.flush();
        return PostDto.from(post);
    }

    @Transactional
    public void delete(Long id) {
        postRepository.delete(getPost(id));
    }

    private Post getPost(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "게시글을 찾을 수 없습니다. id=" + id));
    }
}
