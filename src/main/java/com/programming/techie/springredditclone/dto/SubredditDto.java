package com.programming.techie.springredditclone.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SubredditDto {
    private String name;
    private Integer postCount;
}
