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

import java.util.List;

import static com.programming.techie.springredditclone.util.Constants.POST_NOT_FOUND_FOR_ID;
import static com.programming.techie.springredditclone.util.DateUtils.calcDuration;
import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Transactional(readOnly = true)
    public List<CommentsDto> getAll(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException(POST_NOT_FOUND_FOR_ID + postId));
        return commentRepository.findByPost(post)
                .stream()
                .map(this::mapToDto)
                .collect(toList());
    }

    private CommentsDto mapToDto(Comment comment) {
        return CommentsDto.builder().id(comment.getId())
                .text(comment.getText())
                .duration(calcDuration(comment.getCreatedDate()))
                .build();
    }
}
