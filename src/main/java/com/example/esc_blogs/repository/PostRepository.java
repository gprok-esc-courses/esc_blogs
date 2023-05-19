package com.example.esc_blogs.repository;

import com.example.esc_blogs.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
