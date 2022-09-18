package com.miniproject.domain.user.service;

import com.miniproject.domain.user.entity.User;
import com.miniproject.global.entity.Result;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author : 박수현
 * @version : 1.0.0
 * @package : com.miniproject.domain.user.service
 * @name : UserService
 * @create-date: 2022-09-19
 * @update-date :
 * @update-author : 000
 * @update-description :
 */
public interface UserService {
    public User loadUserByUsername(String username) throws UsernameNotFoundException;

    public Boolean emailValidation(String email);

    public Result createUser(User user);

    public Result retrieveUserByEmail(String email);

    Result updateUserInfo(String email, User user);

    Result updateUserInfoSettings(String email, User user);

    Result updateUserInfoPassword(String email, User user);

    public Result deleteUser(String email, User user);
}
