package com.programming.techie.springredditclone.service;

import com.programming.techie.springredditclone.dto.PostRequest;
import com.programming.techie.springredditclone.dto.PostResponse;
import com.programming.techie.springredditclone.dto.VoteDto;
import com.programming.techie.springredditclone.exception.PostNotFoundException;
import com.programming.techie.springredditclone.exception.SubredditNotFoundException;
import com.programming.techie.springredditclone.model.Post;
import com.programming.techie.springredditclone.model.Subreddit;
import com.programming.techie.springredditclone.model.Vote;
import com.programming.techie.springredditclone.repository.PostRepository;
import com.programming.techie.springredditclone.repository.SubredditRepository;
import com.programming.techie.springredditclone.repository.VoteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import static com.programming.techie.springredditclone.model.VoteType.UPVOTE;
import static com.programming.techie.springredditclone.util.Constants.SUBREDDIT_NOT_FOUND_WITH_ID;

@Service
@AllArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final VoteRepository voteRepository;
    private final SubredditRepository subredditRepository;

    @Transactional(readOnly = true)
    public PostResponse getPost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException("Post Not Found for id - " + id));
        return mapToDto(post);
    }

    static PostResponse mapToDto(Post post) {
        return PostResponse.builder()
                .postName(post.getPostName())
                .description(post.getDescription())
                .url(post.getUrl())
                .userName(post.getUser().getUsername())
                .build();
    }

    @Transactional
    public void save(@Valid PostRequest postRequest) {
        postRepository.save(mapToPost(postRequest));
    }

    private Post mapToPost(PostRequest postRequest) {
        Subreddit subreddit = subredditRepository.findById(postRequest.getSubredditId())
                .orElseThrow(() -> new SubredditNotFoundException(SUBREDDIT_NOT_FOUND_WITH_ID
                        + postRequest.getSubredditId()));

        return Post.builder().postName(postRequest.getPostName())
                .description(postRequest.getDescription())
                .url(postRequest.getUrl())
                .createdDate(Instant.now())
                .voteCount(0)
                .subreddit(subreddit)
                .build();
    }

    public List<PostResponse> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(PostService::mapToDto)
                .collect(Collectors.toList());
    }

    public synchronized void vote(VoteDto voteDto, Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException("Post Not Found with ID - " + id));
        int count = 0;
        if (UPVOTE.equals(voteDto.getVoteType())) {
            count = post.getVoteCount() + 1;
        } else {
            count = post.getVoteCount() - 1;
        }
        voteRepository.save(mapToVote(voteDto, post));
        post.setVoteCount(count);
        postRepository.save(post);
    }

    private Vote mapToVote(VoteDto voteDto, Post post) {
        return Vote.builder()
                .voteType(voteDto.getVoteType())
                .post(post)
                .build();
    }
}
