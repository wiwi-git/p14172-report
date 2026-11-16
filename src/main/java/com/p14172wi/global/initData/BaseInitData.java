package com.p14172wi.global.initData;

import com.p14172wi.domain.post.post.entity.Post;
import com.p14172wi.domain.post.post.repository.PostRepository;
import com.p14172wi.domain.post.postComment.entity.PostComment;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BaseInitData {

    private final PostRepository postRepository;

    public BaseInitData(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Bean
    public ApplicationRunner baseInitDataApplicationRunner() {
        return args -> work();
    }

    private void work() {
        // 이미 데이터가 있으면 다시 넣지 않음
        if (postRepository.count() > 0) return;

        Post post1 = new Post("제목 1", "내용 1");
        post1.addComment(new PostComment("댓글 1-1"));
        post1.addComment(new PostComment("댓글 1-2"));

        Post post2 = new Post("제목 2", "내용 2");
        post2.addComment(new PostComment("댓글 2-1"));
        post2.addComment(new PostComment("댓글 2-2"));

        Post post3 = new Post("제목 3", "내용 3");
        post3.addComment(new PostComment("댓글 3-1"));

        // Post -> PostComment cascade 로 댓글까지 함께 저장됨
        postRepository.save(post1);
        postRepository.save(post2);
        postRepository.save(post3);
    }
}
