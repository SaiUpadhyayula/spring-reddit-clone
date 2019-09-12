package com.programming.techie.springredditclone.repository;

import com.programming.techie.springredditclone.model.Community;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityRepository extends JpaRepository<Community, Long> {
}
