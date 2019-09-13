package com.programming.techie.springredditclone.model;

import lombok.Data;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@Entity
public class Post {
    @Id
    @GeneratedValue
    private Long id;
    @NotBlank(message = "Post Name cannot be empty or Null")
    private String postName;
    @Nullable
    private String url;
    @Nullable
    private String description;
    @OneToMany(mappedBy = "post")
    private List<Vote> votes;
    @OneToMany(mappedBy = "post")
    private List<Comment> comments;
    @ManyToOne
    private User user;
}
