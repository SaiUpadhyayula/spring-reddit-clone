package com.programming.techie.springredditclone.controller;

import com.programming.techie.springredditclone.dto.PostResponse;
import com.programming.techie.springredditclone.dto.SubredditDto;
import com.programming.techie.springredditclone.service.SubredditService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.programming.techie.springredditclone.util.ApiPaths.*;

@RestController
@RequestMapping(SUBREDDIT_API_MAPPING)
@AllArgsConstructor
public class SubredditController {

    private final SubredditService subredditService;

    @GetMapping(QUERY_ALL)
    public List<SubredditDto> getAllSubreddits() {
        return subredditService.getAll();
    }

    @GetMapping(ID_POSTS_ALL)
    public List<PostResponse> getAllPostsInSubreddit(@PathVariable Long id) {
        return subredditService.getAllPosts(id);
    }

    @PostMapping(CREATE)
    public SubredditDto create(@RequestBody @Valid SubredditDto subredditDto) {
        return subredditService.save(subredditDto);
    }
}
