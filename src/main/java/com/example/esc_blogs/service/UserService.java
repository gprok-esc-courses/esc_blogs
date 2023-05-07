package com.example.esc_blogs.service;

import com.example.esc_blogs.model.User;

public interface UserService {
    User findUserByEmail(String email);
}
