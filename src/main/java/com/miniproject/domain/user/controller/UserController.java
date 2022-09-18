package com.miniproject.domain.user.controller;

import com.miniproject.domain.user.entity.User;
import com.miniproject.domain.user.repository.UserRepository;
import com.miniproject.domain.user.service.UserService;
import com.miniproject.global.entity.Result;
import com.miniproject.global.jwt.service.JwtTokenProvider;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

/**
 * @author : 박수현
 * @version : 1.0.0
 * @package : com.miniproject.domain.user.controller
 * @name : UserController
 * @create-date: 2022-09-19
 * @update-date :
 * @update-author : 000
 * @update-description :
 */
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

    @PostMapping("/email/validation")
    public Result emailValidation(@ModelAttribute User user) {
        Result result = new Result();
        JSONObject validation = new JSONObject();
        validation.put("validation", !userService.emailValidation(user.getEmail()));
        result.setPayload(validation);
        return result;
    }

    @PostMapping("/join")
    public Result join(@ModelAttribute User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Collections.singletonList("ROLE_USER"));
        Result result = userService.createUser(user);
        return result;
    }

    @PostMapping("/login")
    public Result login(@ModelAttribute User userInput) {
        User user = userRepository.findByEmailIsNotDeleted(userInput.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 E-MAIL 입니다."));
        if (!passwordEncoder.matches(userInput.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }
        Result result = new Result();
        JSONObject token = new JSONObject();
        token.put("token", jwtTokenProvider.createToken(user.getEmail(), user.getRoles()));
        result.setPayload(token);
        return result;
    }
}
