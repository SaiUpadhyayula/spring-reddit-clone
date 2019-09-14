package com.programming.techie.springredditclone.dto;

import com.programming.techie.springredditclone.model.Vote;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class PostResponse {
    private String postName;
    private String url;
    private String description;
    private Integer votesNum;
    private String userName;
    private boolean upVote;
    private boolean downVote;
    private String subredditName;
    private Integer commentNum;
}
