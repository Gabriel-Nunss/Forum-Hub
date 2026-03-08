package com.forumHub.ForumHub.repository;

import com.forumHub.ForumHub.domain.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {
}