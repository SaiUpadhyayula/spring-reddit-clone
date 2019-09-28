package com.programming.techie.springredditclone.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostRequest {
    private Long postId;
    @NotNull(message = "Subreddit ID is required")
    private Long subredditId;
    @NotBlank(message = "Post Name is required")
    private String postName;
    private String url;
    private String description;
    @NotBlank(message = "Username is required")
    private String userName;
}
