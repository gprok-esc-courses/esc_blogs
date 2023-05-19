package com.example.esc_blogs.controller;

import com.example.esc_blogs.model.Blog;
import com.example.esc_blogs.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
public class GuestController {


    private BlogRepository blogRepository;

    @Autowired
    public GuestController(BlogRepository br) {
        blogRepository = br;
    }



    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/blogs")
    public String blogs(Model model) {
        List<Blog> blogs = blogRepository.findAll();
        model.addAttribute("blogs", blogs);
        return "blogs";
    }

    @GetMapping("/blog/{slug}")
    public String blog(@PathVariable String slug, Model model) {
        Blog blog = blogRepository.findBySlug(slug);
        model.addAttribute("blog", blog);
        return "blog";
    }

}
