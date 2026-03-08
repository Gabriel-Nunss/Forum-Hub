package com.forumHub.ForumHub.repository;

import com.forumHub.ForumHub.domain.Topico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
}