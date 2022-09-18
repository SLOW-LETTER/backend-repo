package com.miniproject.domain.user.service;

import com.miniproject.domain.user.entity.User;
import com.miniproject.global.entity.Result;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService {
    public User loadUserByUsername(String username) throws UsernameNotFoundException;
    public Boolean emailValidation(String email);
    public Result createUser(User user);
    public Result retrieveUserList();
    public Result retrieveUser(Long userId);
    public Result retrieveUserByEmail(String email);
    Result updateUser(User user, Long userId);
    public Result deleteUser(Long user);
}
