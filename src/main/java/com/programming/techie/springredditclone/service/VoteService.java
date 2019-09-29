package com.programming.techie.springredditclone.service;

import com.programming.techie.springredditclone.dto.PostResponse;
import com.programming.techie.springredditclone.dto.VoteDto;
import com.programming.techie.springredditclone.exception.PostNotFoundException;
import com.programming.techie.springredditclone.exception.SpringRedditException;
import com.programming.techie.springredditclone.model.Post;
import com.programming.techie.springredditclone.model.Vote;
import com.programming.techie.springredditclone.repository.PostRepository;
import com.programming.techie.springredditclone.repository.VoteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.programming.techie.springredditclone.model.VoteType.UPVOTE;
import static com.programming.techie.springredditclone.util.Constants.POST_NOT_FOUND_FOR_ID;

@Service
@AllArgsConstructor
public class VoteService {

    private final VoteRepository voteRepository;
    private final PostRepository postRepository;
    private final PostService postService;
    private final AuthService authService;

    @Transactional
    public synchronized PostResponse vote(VoteDto voteDto) {
        Post post = postRepository.findById(voteDto.getPostId())
                .orElseThrow(() -> new PostNotFoundException("Post Not Found with ID - " + voteDto.getPostId()));
        Optional<Vote> voteByPostAndUser = voteRepository.findTopByPostAndUserOrderByVoteIdDesc(post, authService.getCurrentUser());
        if (voteByPostAndUser.isPresent()) {
            if (voteByPostAndUser.get().getVoteType().equals(voteDto.getVoteType())) {
                throw new SpringRedditException("You have already " + voteDto.getVoteType() + "'d for this post");
            }
        }
        int count = 0;
        if (UPVOTE.equals(voteDto.getVoteType())) {
            count = post.getVoteCount() + 1;
        } else {
            count = post.getVoteCount() - 1;
        }
        voteRepository.save(mapToVote(voteDto, post));
        post.setVoteCount(count);
        postRepository.save(post);
        return postService.mapToDto(post);
    }

    public Integer getVoteNumber(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException(POST_NOT_FOUND_FOR_ID + postId));
        return post.getVoteCount();
    }

    private Vote mapToVote(VoteDto voteDto, Post post) {
        return Vote.builder()
                .voteType(voteDto.getVoteType())
                .post(post)
                .user(authService.getCurrentUser())
                .build();
    }
}
