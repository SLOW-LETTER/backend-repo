package com.miniproject.domain.user.controller;

import com.miniproject.domain.user.entity.User;
import com.miniproject.domain.user.repository.UserRepository;
import com.miniproject.domain.user.service.UserService;
import com.miniproject.global.entity.Result;
import com.miniproject.global.jwt.service.JwtTokenProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "api/v1/user")
public class UserInfoController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public UserInfoController(PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider) {
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @GetMapping
    public Result getUserInfo(@RequestHeader("X-AUTH-TOKEN") String token) {
        log.info("email >>> "+jwtTokenProvider.getUserPk(token));
        String email = jwtTokenProvider.getUserPk(token);
        Result result = userService.retrieveUserByEmail(email);
        return result;
    }

    @GetMapping("/{user_id}")
    public Result retrieveUser(@PathVariable Long user_id) {
        Result result = userService.retrieveUser(user_id);
        return result;
    }

    @PatchMapping("/{user_id}")
    public Result updateUser(@ModelAttribute User user, @PathVariable Long user_id) {
        Result result = userService.updateUser(user, user_id);
        return result;
    }

    @DeleteMapping("/{user_id}")
    public Result deleteUser(@PathVariable Long user_id) {
        Result result = userService.deleteUser(user_id);
        return result;
    }
}
