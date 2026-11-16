package com.p14172wi.domain.post.postComment.entity;

import com.p14172wi.domain.post.post.entity.Post;
import com.p14172wi.global.jpa.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "post_comments")
public class PostComment extends BaseEntity {

    @Column(nullable = false, length = 1000)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    protected PostComment() {
    }

    public PostComment(String content) {
        this.content = content;
    }

    public void update(String content) {
        this.content = content;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public String getContent() {
        return content;
    }

    public Post getPost() {
        return post;
    }
}
