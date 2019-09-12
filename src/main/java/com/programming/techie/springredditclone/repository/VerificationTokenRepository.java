package com.programming.techie.springredditclone.repository;

import com.programming.techie.springredditclone.model.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
}
