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

import java.util.Collections;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "api/v1/users")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public UserController(PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider) {
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    // 회원가입
    @PostMapping("/join")
    public Long join(@RequestBody Map<String, String> user) {
        return userRepository.save(User.builder()
                .email(user.get("email"))
                .password(passwordEncoder.encode(user.get("password")))
                .roles(Collections.singletonList("ROLE_USER")) // 최초 가입시 USER 로 설정
                .build()).getId();
    }

    // 로그인
    @PostMapping("/login")
    public String login(@RequestBody Map<String, String> userInput) {
        User user = userRepository.findByEmail(userInput.get("email"))
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 E-MAIL 입니다."));
        if (!passwordEncoder.matches(userInput.get("password"), user.getPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }
        return jwtTokenProvider.createToken(user.getEmail(), user.getRoles());
    }

    @GetMapping
    public Result retrieveUser() {
        Result result = userService.retrieveUserList();
        return result;
    }

    @GetMapping("/{user_id}")
    public Result retrieveUser(@PathVariable Long user_id) {
        Result result = userService.retrieveUser(user_id);
        return result;
    }

    @PostMapping
    public Result createUser(@ModelAttribute User user) {
        Result result = userService.createUser(user);
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
