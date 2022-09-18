package com.miniproject.domain.user.controller;

import com.miniproject.domain.user.dto.UserDto;
import com.miniproject.domain.user.entity.User;
import com.miniproject.domain.user.repository.UserRepository;
import com.miniproject.domain.user.service.UserService;
import com.miniproject.global.entity.Result;
import com.miniproject.global.file.service.S3Service;
import com.miniproject.global.jwt.service.JwtTokenProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * @author : 박수현
 * @version : 1.0.0
 * @package : com.miniproject.domain.user.controller
 * @name : UserInfoController
 * @create-date: 2022-09-19
 * @update-date :
 * @update-author : 000
 * @update-description :
 */
@Slf4j
@RestController
@RequestMapping(value = "api/v1/users-info")
public class UserInfoController {

    private final S3Service s3Service;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public UserInfoController(PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider, S3Service s3Service) {
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.s3Service = s3Service;
    }

    @GetMapping
    public Result getUserInfo(@RequestHeader("X-AUTH-TOKEN") String token) {
        log.info("email >>> " + jwtTokenProvider.getUserPk(token));
        String email = jwtTokenProvider.getUserPk(token);
        Result result = userService.retrieveUserByEmail(email);
        return result;
    }

    @PatchMapping
    public Result updateUserInfo(@RequestHeader("X-AUTH-TOKEN") String token, @ModelAttribute UserDto userDto) throws IOException {
        User user = userDto.toEntity();
        if (userDto.getFile() != null) {
            String url = s3Service.uploadFile(userDto.getFile(), "users");
            user.setProfile_image_url(url);
        }
        String email = jwtTokenProvider.getUserPk(token);
        Result result = userService.updateUserInfo(email, user);
        return result;
    }

    @PatchMapping("/settings")
    public Result updateUserInfoSettings(@RequestHeader("X-AUTH-TOKEN") String token, @ModelAttribute User user) {
        String email = jwtTokenProvider.getUserPk(token);
        Result result = userService.updateUserInfoSettings(email, user);
        return result;
    }

    @PatchMapping("/password")
    public Result updateUserInfoPassword(@RequestHeader("X-AUTH-TOKEN") String token, @ModelAttribute UserDto userDto) {
        String email = jwtTokenProvider.getUserPk(token);

        User user = userRepository.findByEmailIsNotDeleted(email)
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 E-MAIL 입니다."));
        if (!passwordEncoder.matches(userDto.getOldPassword(), user.getPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }

        user.setPassword(passwordEncoder.encode(userDto.getNewPassword()));
        Result result = userService.updateUserInfoPassword(email, user);
        return result;
    }

    @DeleteMapping
    public Result deleteUser(@RequestHeader("X-AUTH-TOKEN") String token, @ModelAttribute User userInput) {
        String email = jwtTokenProvider.getUserPk(token);

        User user = userRepository.findByEmailIsNotDeleted(email)
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 E-MAIL 입니다."));
        if (!passwordEncoder.matches(userInput.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }
        user.setWithdraw_feedback(userInput.getWithdraw_feedback());

        Result result = userService.deleteUser(email, user);
        return result;
    }
}
