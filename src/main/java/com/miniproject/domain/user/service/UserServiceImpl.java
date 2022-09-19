package com.miniproject.domain.user.service;

import com.miniproject.domain.user.entity.User;
import com.miniproject.domain.user.repository.UserRepository;
import com.miniproject.global.entity.ErrorResponse;
import com.miniproject.global.entity.Result;
import com.miniproject.global.enumpkg.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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
    public Result createUser(User user) {
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
            if (optionalUser.get().getIs_deleted() == false) {
                result.setPayload(optionalUser.get());
            }
        } else {
            result.setMessage(ErrorResponse.of(ErrorCode.PA02));
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
            if (user.getProfile_image_url() != null) {
                updateUser.get().setProfile_image_url(user.getProfile_image_url());
            }
            user = userRepository.save(updateUser.get());
            result.setPayload(user);
        } else {
            result.setMessage(ErrorResponse.of(ErrorCode.PA02));
        }
        return result;
    }

    @Override
    public Result updateUserInfoSettings(String email, User user) {
        Optional<User> updateUser = userRepository.findByEmailIsNotDeleted(email);
        Result result = new Result();
        if (updateUser.isPresent()) {
            if (user.getIs_checked_my_receive() != null) {
                updateUser.get().setIs_checked_my_receive(user.getIs_checked_my_receive());
            }
            if (user.getIs_checked_my_send() != null) {
                updateUser.get().setIs_checked_my_send(user.getIs_checked_my_send());
            }
            if (user.getIs_checked_other_receive() != null) {
                updateUser.get().setIs_checked_other_receive(user.getIs_checked_other_receive());
            }
            if (user.getIs_checked_other_send() != null) {
                updateUser.get().setIs_checked_other_send(user.getIs_checked_other_send());
            }
            user = userRepository.save(updateUser.get());
            result.setPayload(user);
        } else {
            result.setMessage(ErrorResponse.of(ErrorCode.PA02));
        }
        return result;
    }

    @Override
    public Result updateUserInfoPassword(String email, User user) {
        Optional<User> updateUser = userRepository.findByEmailIsNotDeleted(email);
        Result result = new Result();
        if (updateUser.isPresent()) {
            if (user.getPassword() != null) {
                updateUser.get().setPassword(user.getPassword());
            }
            user = userRepository.save(updateUser.get());
            result.setPayload(user);
        } else {
            result.setMessage(ErrorResponse.of(ErrorCode.PA02));
        }
        return result;
    }

    @Override
    public Result deleteUser(String email, User user) {
        Optional<User> updateUser = userRepository.findByEmailIsNotDeleted(email);
        Result result = new Result();
        if (updateUser.isPresent()) {
            userRepository.deletedUserByEmail(email, user.getWithdraw_feedback());
            // result.setPayload(user);
        } else {
            result.setMessage(ErrorResponse.of(ErrorCode.PA02));
        }
        return result;
    }
}
