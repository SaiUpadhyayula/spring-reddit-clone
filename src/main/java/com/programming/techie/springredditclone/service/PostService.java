package com.programming.techie.springredditclone.service;

import com.programming.techie.springredditclone.exception.PostNotFoundException;
import com.programming.techie.springredditclone.model.Post;
import com.programming.techie.springredditclone.repository.PostRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post getPost(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException("Post Not Found for id - " + id));
    }

    public void save(Post post) {
        postRepository.save(post);
    }
}
