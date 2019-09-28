package com.programming.techie.springredditclone.service;

import com.programming.techie.springredditclone.dto.CommentsDto;
import com.programming.techie.springredditclone.exception.PostNotFoundException;
import com.programming.techie.springredditclone.model.Comment;
import com.programming.techie.springredditclone.model.Post;
import com.programming.techie.springredditclone.repository.CommentRepository;
import com.programming.techie.springredditclone.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.time.Instant;
import java.util.List;

import static com.programming.techie.springredditclone.util.Constants.POST_NOT_FOUND_FOR_ID;
import static com.programming.techie.springredditclone.util.Constants.POST_URL;
import static com.programming.techie.springredditclone.util.DateUtils.calcDuration;
import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final MailContentBuilder mailContentBuilder;
    private final MailService mailService;
    private final AuthService authService;

    @Transactional(readOnly = true)
    public List<CommentsDto> getCommentByPost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException(POST_NOT_FOUND_FOR_ID + postId));
        return commentRepository.findByPost(post)
                .stream()
                .map(this::mapToDto)
                .collect(toList());
    }

    @Transactional
    public void createComment(CommentsDto commentsDto) {
        Post post = postRepository.findById(commentsDto.getPostId())
                .orElseThrow(() -> new PostNotFoundException(POST_NOT_FOUND_FOR_ID));
        commentRepository.save(mapToComment(commentsDto, post));
        String message = mailContentBuilder.build(post.getUser().getUsername() + " posted a comment on your post."
                + POST_URL + post.getPostId());
        sendCommentNotification(message, post.getUser().getEmail());
    }

    private CommentsDto mapToDto(Comment comment) {
        return CommentsDto.builder().id(comment.getId())
                .text(comment.getText())
                .duration(calcDuration(comment.getCreatedDate()))
                .username(comment.getUser().getUsername())
                .build();
    }

    private void sendCommentNotification(String message, @Email @NotEmpty(message = "Email is required") String email) {
        mailService.sendMail(email, message);
    }

    private Comment mapToComment(CommentsDto commentsDto, Post post) {
        return Comment.builder()
                .text(commentsDto.getText())
                .createdDate(Instant.now())
                .post(post)
                .user(authService.getCurrentUser())
                .build();
    }
}
