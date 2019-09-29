package com.programming.techie.springredditclone.controller;

import com.programming.techie.springredditclone.dto.CommentsDto;
import com.programming.techie.springredditclone.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.programming.techie.springredditclone.util.ApiPaths.QUERY_POST_ID;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/comments")
@AllArgsConstructor
public class CommentsController {

    private final CommentService commentService;

    @GetMapping(QUERY_POST_ID)
    public List<CommentsDto> getAllCommentsForPost(@PathVariable Long postId) {
        return commentService.getCommentByPost(postId);
    }

    @GetMapping("/query/user/{userName}")
    public List<CommentsDto> getAllCommentsByUser(@PathVariable String userName) {
        return commentService.getCommentsByUser(userName);
    }

    @PostMapping("/create")
    public ResponseEntity createComment(@RequestBody CommentsDto commentsDto) {
        commentService.createComment(commentsDto);
        return new ResponseEntity(OK);
    }
}
