package com.programming.techie.springredditclone.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class CommentsDto {
    private Long id;
    private String text;
    private String duration;
}
