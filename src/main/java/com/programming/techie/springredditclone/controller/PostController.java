package com.programming.techie.springredditclone.controller;

import com.programming.techie.springredditclone.model.Comment;
import com.programming.techie.springredditclone.model.Post;
import com.programming.techie.springredditclone.service.CommentService;
import com.programming.techie.springredditclone.service.PostService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/post")
@AllArgsConstructor
@NoArgsConstructor
public class PostController {

    private final PostService postService;
    private final CommentService commentService;

    public PostController(PostService postService,
                          CommentService commentService) {
        this.postService = postService;
        this.commentService = commentService;
    }

    @GetMapping("/")
    public ModelAndView getPostPage() {
        return new ModelAndView("post");
    }

    @GetMapping("/{id}")
    public ModelAndView getPost(@PathVariable Long id, ModelAndView modelAndView) {
        Post post = postService.getPost(id);
        List<Comment> comments = commentService.getCommentForPost(post);
        modelAndView.setViewName("post");
        modelAndView.addObject("post", post);
        modelAndView.addObject("comments", comments);
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView createPost(@Valid @ModelAttribute("post") Post post,
                                   ModelAndView modelAndView,
                                   BindingResult bindingResult) {
        return null;
    }
}
