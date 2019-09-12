package com.programming.techie.springredditclone.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "community")
public class Community {
    @Id
    @GeneratedValue
    private Long id;
    @NotBlank(message = "Community name is required")
    private String name;
    @OneToMany
    private List<Post> posts;
}
