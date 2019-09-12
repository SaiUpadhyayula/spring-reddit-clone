package com.programming.techie.springredditclone.repository;

import com.programming.techie.springredditclone.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Long> {
}
