package com.miniproject.domain.user.service;

import com.miniproject.domain.user.dto.UserDto;
import com.miniproject.domain.user.entity.User;
import com.miniproject.domain.user.repository.UserRepository;
import com.miniproject.global.error.service.CustomException;
import com.miniproject.global.error.service.ErrorResponse;
import com.miniproject.global.entity.Result;
import com.miniproject.global.enumpkg.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;

/**
 * @author : 박수현
 * @version : 1.0.0
 * @package : com.miniproject.domain.user.service
 * @name : UserServiceImpl
 * @create-date: 2022-09-19
 * @update-date :
 * @update-author : 000
 * @update-description :
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
    }

    @Override
    public Boolean emailValidation(String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        return optionalUser.isPresent();
    }

    @Override
    public Result createUser(UserDto userDto, PasswordEncoder passwordEncoder) {
        User user = userDto.toEntity();
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRoles(Collections.singletonList("ROLE_USER"));
        user = userRepository.save(user);
        Result result = new Result();
        result.setPayload(user);
        return result;
    }

    @Override
    public Result retrieveUserByEmail(String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        Result result = new Result();
        if (optionalUser.isPresent()) {
            if (optionalUser.get().getIsDeleted() == false) {
                result.setPayload(optionalUser.get());
            }
        } else {
            result.setMessage(ErrorCode.PA02);
            throw new CustomException(result);
        }
        return result;
    }

    @Override
    public Result updateUserInfo(String email, User user) {
        Optional<User> updateUser = userRepository.findByEmailIsNotDeleted(email);
        Result result = new Result();
        if (updateUser.isPresent()) {
            if (user.getName() != null) {
                updateUser.get().setName(user.getName());
            }
            if (user.getPhone() != null) {
                updateUser.get().setPhone(user.getPhone());
            }
            if (user.getBio() != null) {
                updateUser.get().setBio(user.getBio());
            }
            if (user.getProfileImageUrl() != null) {
                updateUser.get().setProfileImageUrl(user.getProfileImageUrl());
            }
            user = userRepository.save(updateUser.get());
            result.setPayload(user);
        } else {
            result.setMessage(ErrorCode.PA02);
            throw new CustomException(result);
        }
        return result;
    }

    @Override
    public Result updateToken(String token, User user) {
        // Optional<User> updateUser = userRepository.findByEmailIsNotDeleted(user.getEmail());
        Result result = new Result();
        if (user != null) {
            userRepository.updateToken(token, user.getEmail());
            // result.setPayload("");
        } else {
            result.setMessage(ErrorCode.PA02);
            throw new CustomException(result);
        }
        return result;
    }

    @Override
    public Result updateUserInfoSettings(String email, UserDto userDto) {
        User user = userDto.toEntity();
        Optional<User> updateUser = userRepository.findByEmailIsNotDeleted(email);
        Result result = new Result();
        if (updateUser.isPresent()) {
            if (user.getIsCheckedMyReceive() != null) {
                updateUser.get().setIsCheckedMyReceive(user.getIsCheckedMyReceive());
            }
            if (user.getIsCheckedMySend() != null) {
                updateUser.get().setIsCheckedMySend(user.getIsCheckedMySend());
            }
            if (user.getIsCheckedOtherReceive() != null) {
                updateUser.get().setIsCheckedOtherReceive(user.getIsCheckedOtherReceive());
            }
            if (user.getIsCheckedOtherSend() != null) {
                updateUser.get().setIsCheckedOtherSend(user.getIsCheckedOtherSend());
            }
            user = userRepository.save(updateUser.get());
            result.setPayload(user);
        } else {
            result.setMessage(ErrorCode.PA02);
            throw new CustomException(result);
        }
        return result;
    }

    @Override
    public Result updateUserInfoPassword(String email, UserDto userDto, PasswordEncoder passwordEncoder) {
        User user = userRepository.findByEmailIsNotDeleted(email)
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 E-MAIL 입니다."));
        if (!passwordEncoder.matches(userDto.getOldPassword(), user.getPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }
        user.setPassword(passwordEncoder.encode(userDto.getNewPassword()));

        Optional<User> updateUser = userRepository.findByEmailIsNotDeleted(email);
        Result result = new Result();
        if (updateUser.isPresent()) {
            if (user.getPassword() != null) {
                updateUser.get().setPassword(user.getPassword());
            }
            user = userRepository.save(updateUser.get());
            result.setPayload(user);
        } else {
            result.setMessage(ErrorCode.PA02);
            throw new CustomException(result);
        }
        return result;
    }

    @Override
    public Result deleteUser(String email, UserDto userDto, PasswordEncoder passwordEncoder) {
        User user = userRepository.findByEmailIsNotDeleted(email)
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 E-MAIL 입니다."));
        if (!passwordEncoder.matches(userDto.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }
        user.setWithdrawFeedback(userDto.getWithdrawFeedback());

        Optional<User> updateUser = userRepository.findByEmailIsNotDeleted(email);
        Result result = new Result();
        if (updateUser.isPresent()) {
            userRepository.deletedUserByEmail(email, user.getWithdrawFeedback());
            // result.setPayload(user);
        } else {
            result.setMessage(ErrorCode.PA02);
            throw new CustomException(result);
        }
        return result;
    }
}
