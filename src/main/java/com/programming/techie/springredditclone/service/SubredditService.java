package com.programming.techie.springredditclone.service;

import com.programming.techie.springredditclone.dto.PostResponse;
import com.programming.techie.springredditclone.dto.SubredditDto;
import com.programming.techie.springredditclone.exception.SubredditNotFoundException;
import com.programming.techie.springredditclone.model.Subreddit;
import com.programming.techie.springredditclone.repository.PostRepository;
import com.programming.techie.springredditclone.repository.SubredditRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.programming.techie.springredditclone.util.Constants.SUBREDDIT_NOT_FOUND_WITH_ID;
import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class SubredditService {

    private final SubredditRepository subredditRepository;
    private final PostRepository postRepository;

    public List<SubredditDto> getAll() {
        return subredditRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(toList());
    }

    private SubredditDto mapToDto(Subreddit subreddit) {
        return SubredditDto.builder().name(subreddit.getName())
                .postCount(subreddit.getPosts().size())
                .build();
    }

    public List<PostResponse> getAllPosts(Long id) {
        Subreddit subreddit = subredditRepository.findById(id)
                .orElseThrow(() -> new SubredditNotFoundException(SUBREDDIT_NOT_FOUND_WITH_ID + id));
        return postRepository.findAllBySubreddit(subreddit)
                .stream()
                .map(PostService::mapToDto)
                .collect(toList());
    }
}
