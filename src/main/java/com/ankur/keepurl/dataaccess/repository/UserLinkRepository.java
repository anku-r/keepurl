package com.ankur.keepurl.dataaccess.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ankur.keepurl.dataaccess.entity.UserLink;

public interface UserLinkRepository extends JpaRepository<UserLink, Long> {

	public Optional<UserLink> findByTitle(String title);
}
