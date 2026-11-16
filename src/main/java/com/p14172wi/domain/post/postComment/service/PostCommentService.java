package com.p14172wi.domain.post.postComment.service;

import com.p14172wi.domain.post.postComment.entity.PostComment;
import com.p14172wi.domain.post.post.entity.Post;
import com.p14172wi.domain.post.postComment.dto.PostCommentRequest;
import com.p14172wi.domain.post.postComment.dto.PostCommentDto;
import com.p14172wi.domain.post.postComment.repository.PostCommentRepository;
import com.p14172wi.domain.post.post.repository.PostRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class PostCommentService {

    private final PostCommentRepository commentRepository;
    private final PostRepository postRepository;

    public PostCommentService(PostCommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    public List<PostCommentDto> findAllByPost(Long postId) {
        getPost(postId);
        return commentRepository.findAllByPostIdOrderByIdAsc(postId).stream()
                .map(PostCommentDto::from)
                .toList();
    }

    @Transactional
    public PostCommentDto create(Long postId, PostCommentRequest request) {
        Post post = getPost(postId);
        PostComment comment = new PostComment(request.content());
        post.addComment(comment);
        commentRepository.save(comment);
        return PostCommentDto.from(comment);
    }

    @Transactional
    public PostCommentDto update(Long postId, Long commentId, PostCommentRequest request) {
        PostComment comment = getComment(postId, commentId);
        comment.update(request.content());
        commentRepository.flush();
        return PostCommentDto.from(comment);
    }

    @Transactional
    public void delete(Long postId, Long commentId) {
        PostComment comment = getComment(postId, commentId);
        comment.getPost().removeComment(comment);
    }

    private Post getPost(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "게시글을 찾을 수 없습니다. id=" + postId));
    }

    private PostComment getComment(Long postId, Long commentId) {
        return commentRepository.findByIdAndPostId(commentId, postId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "댓글을 찾을 수 없습니다. id=" + commentId));
    }
}
