package com.programming.techie.springredditclone.repository;

import com.programming.techie.springredditclone.model.Subreddit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubredditRepository extends JpaRepository<Subreddit, Long> {
}
