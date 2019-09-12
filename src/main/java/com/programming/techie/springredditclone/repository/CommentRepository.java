package com.programming.techie.springredditclone.repository;

import com.programming.techie.springredditclone.model.Comment;
import com.programming.techie.springredditclone.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPost(Post post);
}
