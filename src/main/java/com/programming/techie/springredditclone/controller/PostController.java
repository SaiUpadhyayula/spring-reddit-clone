package com.programming.techie.springredditclone.controller;

import com.programming.techie.springredditclone.dto.PostRequest;
import com.programming.techie.springredditclone.dto.PostResponse;
import com.programming.techie.springredditclone.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.programming.techie.springredditclone.util.ApiPaths.*;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(POST_API_MAPPINGS)
@AllArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping(QUERY_ID)
    public PostResponse getPost(@PathVariable Long id) {
        return postService.getPost(id);
    }

    @GetMapping(QUERY_ALL)
    public List<PostResponse> getPost() {
        return postService.getAllPosts();
    }

    @GetMapping(QUERY_BY_SUBREDDIT)
    public List<PostResponse> getPostsBySubreddit(@PathVariable Long subredditId) {
        return postService.getPostsBySubreddit(subredditId);
    }

    @GetMapping(QUERY_BY_USERNAME)
    public List<PostResponse> getPostsByUsername(@PathVariable String name) {
        return postService.getPostsByUsername(name);
    }

    @PostMapping(CREATE)
    public ResponseEntity createPost(@Valid @RequestBody PostRequest postRequest) {
        postService.save(postRequest);
        return new ResponseEntity(OK);
    }
}
