package com.miniproject.domain.user.service;

import com.miniproject.domain.user.dto.UserDto;
import com.miniproject.domain.user.entity.User;
import com.miniproject.global.entity.Result;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

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

    public Result createUser(UserDto userDto, PasswordEncoder passwordEncoder);

    public Result retrieveUserByEmail(String email);

    Result updateUserInfo(String email, User user);

    Result updateToken(String email, User user);

    Result updateUserInfoSettings(String email, UserDto userDto);

    Result updateUserInfoPassword(String email, UserDto userDto, PasswordEncoder passwordEncoder);

    public Result deleteUser(String email, UserDto userDto, PasswordEncoder passwordEncoder);
}
