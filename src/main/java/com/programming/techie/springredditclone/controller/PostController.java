package com.programming.techie.springredditclone.controller;

import com.programming.techie.springredditclone.model.Comment;
import com.programming.techie.springredditclone.model.Post;
import com.programming.techie.springredditclone.service.CommentService;
import com.programming.techie.springredditclone.service.PostService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/post")
@AllArgsConstructor
@NoArgsConstructor
public class PostController {

    private PostService postService;
    private CommentService commentService;

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
    public String createPost(@Valid @ModelAttribute("post") Post post,
                             Model model,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("post", post);
            return "/post/create";
        }
        postService.save(post);
        redirectAttributes.addAttribute("id", post.getId());
        return "redirect:/post/{id}";
    }
}
