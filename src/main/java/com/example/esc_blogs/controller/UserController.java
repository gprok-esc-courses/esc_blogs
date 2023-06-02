package com.example.esc_blogs.controller;

import com.example.esc_blogs.dto.PostDto;
import com.example.esc_blogs.model.Blog;
import com.example.esc_blogs.model.Post;
import com.example.esc_blogs.model.User;
import com.example.esc_blogs.repository.BlogRepository;
import com.example.esc_blogs.repository.PostRepository;
import com.example.esc_blogs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Optional;
import java.util.Set;

@Controller
public class UserController {

    UserRepository userRepository;
    BlogRepository blogRepository;
    PostRepository postRepository;

    @Autowired
    public UserController(UserRepository userRepository, BlogRepository blogRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.blogRepository = blogRepository;
        this.postRepository = postRepository;
    }

    @GetMapping("/user/profile")
    public String profile() {
        return "profile";
    }

    @GetMapping("/user/add/post")
    public String addPost(Model model, Authentication authentication) {
        PostDto postDto = new PostDto();
        User user = userRepository.findByEmail(authentication.getName());
        Set<Blog> blogs = user.getBlogs();

        model.addAttribute("postDto", postDto);
        model.addAttribute("blogs", blogs);

        return "add_post";
    }


    @PostMapping("/user/post/save")
    public String savePost(@Valid @ModelAttribute("postDto") PostDto postDto,
                           Model model, Authentication authentication) {
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setSlug(postDto.getSlug());
        post.setContent(postDto.getContent());

        Optional<Blog> blog = blogRepository.findById(postDto.getBlog_id());
        if(blog.isPresent()) {
            post.setBlog(blog.get());
            postRepository.save(post);
            return "redirect:/user/add/post?success";
        }
        else {
            return "redirect:/user/add/post?error";
        }
    }

}
