package com.programming.techie.springredditclone.service;

import com.programming.techie.springredditclone.dto.PostRequest;
import com.programming.techie.springredditclone.dto.PostResponse;
import com.programming.techie.springredditclone.dto.VoteDto;
import com.programming.techie.springredditclone.exception.PostNotFoundException;
import com.programming.techie.springredditclone.exception.SpringRedditException;
import com.programming.techie.springredditclone.exception.SubredditNotFoundException;
import com.programming.techie.springredditclone.model.Post;
import com.programming.techie.springredditclone.model.Subreddit;
import com.programming.techie.springredditclone.model.Vote;
import com.programming.techie.springredditclone.model.VoteType;
import com.programming.techie.springredditclone.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static com.programming.techie.springredditclone.model.VoteType.UPVOTE;
import static com.programming.techie.springredditclone.util.Constants.POST_NOT_FOUND_FOR_ID;
import static com.programming.techie.springredditclone.util.Constants.SUBREDDIT_NOT_FOUND_WITH_ID;
import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final VoteRepository voteRepository;
    private final SubredditRepository subredditRepository;
    private final CommentRepository commentRepository;
    private final AuthService authService;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public PostResponse getPost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException(POST_NOT_FOUND_FOR_ID + id));
        return mapToDto(post);
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(toList());
    }

    @Transactional
    public void save(@Valid PostRequest postRequest) {
        postRepository.save(mapToPost(postRequest));
    }

    @Transactional
    public synchronized void vote(VoteDto voteDto, Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException("Post Not Found with ID - " + id));
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
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getPostsBySubreddit(Long subredditId) {
        Subreddit subreddit = subredditRepository.findById(subredditId)
                .orElseThrow(() -> new SubredditNotFoundException(SUBREDDIT_NOT_FOUND_WITH_ID + subredditId));
        List<Post> posts = postRepository.findAllBySubreddit(subreddit);
        return posts.stream().map(this::mapToDto).collect(toList());
    }

    PostResponse mapToDto(Post post) {
        PostResponse postResponse = PostResponse.builder()
                .id(post.getPostId())
                .postName(post.getPostName())
                .description(post.getDescription())
                .url(post.getUrl())
                .userName(post.getUser().getUsername())
                .subredditName(post.getSubreddit().getName())
                .votesNum(post.getVoteCount())
                .commentNum(commentRepository.findByPost(post).size())
                .build();
        if (authService.isLoggedIn()) {
            Optional<Vote> voteForPostByUser = voteRepository.findTopByPostAndUserOrderByVoteIdDesc(post, authService.getCurrentUser());
            if (voteForPostByUser.isPresent()) {
                VoteType voteType = voteForPostByUser.get().getVoteType();
                if (voteType.equals(UPVOTE))
                    postResponse.setUpVote(true);
                else
                    postResponse.setDownVote(true);
            }
        }
        return postResponse;
    }

    private Post mapToPost(PostRequest postRequest) {
        Subreddit subreddit = subredditRepository.findById(postRequest.getSubredditId())
                .orElseThrow(() -> new SubredditNotFoundException(SUBREDDIT_NOT_FOUND_WITH_ID
                        + postRequest.getSubredditId()));
        return Post.builder()
                .postName(postRequest.getPostName())
                .description(postRequest.getDescription())
                .url(postRequest.getUrl())
                .createdDate(Instant.now())
                .voteCount(0)
                .subreddit(subreddit)
                .user(authService.getCurrentUser())
                .build();
    }

    private Vote mapToVote(VoteDto voteDto, Post post) {
        return Vote.builder()
                .voteType(voteDto.getVoteType())
                .post(post)
                .user(authService.getCurrentUser())
                .build();
    }
}
