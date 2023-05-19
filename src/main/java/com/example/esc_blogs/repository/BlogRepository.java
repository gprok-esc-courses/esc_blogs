package com.example.esc_blogs.repository;

import com.example.esc_blogs.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Blog, Long> {
    Blog findBySlug(String slug);
}
