package com.programming.techie.springredditclone.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SubredditDto {
    private Long id;
    private String name;
    private String description;
    private Integer postCount;
}
