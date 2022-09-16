package com.miniproject.domain.user.service;

import com.miniproject.domain.user.entity.User;
import com.miniproject.domain.user.repository.UserRepository;
import com.miniproject.global.entity.ErrorResponse;
import com.miniproject.global.entity.Result;
import com.miniproject.global.enumpkg.ServiceResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public Result createUser(User user) {
        user = userRepository.save(user);
        Result result = new Result();
        result.setPayload(user);
        return result;
    }

    @Override
    public Result retrieveUserList() {
        List<User> list = userRepository.findAllByOrderByUserIdDesc();
        Result result = new Result();
        result.setPayload(list);
        return result;
    }

    @Override
    public Result retrieveUser(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        Result result = new Result();
        if(optionalUser.isPresent()){
            if(optionalUser.get().getIs_deleted() == false) {
                result.setPayload(optionalUser.get());
            }
        } else{
            result.setMessage(new ErrorResponse(ServiceResult.NOTEXIST.toString()));
        }
        return result;
    }

    @Override
    public Result updateUser(User user, Long userId) {
        Optional<User> updateUser = userRepository.findById(userId);
        Result result = new Result();
        if(updateUser.isPresent()){
            user= userRepository.save(updateUser.get());
            result.setPayload(user);
        } else{
            result.setMessage(new ErrorResponse(ServiceResult.NOTEXIST.toString()));
        }
        return result;
    }

    @Override
    public Result deleteUser(Long userId) {
        Result result = new Result();
        boolean isPresent = userRepository.findById(userId).isPresent();
        if(!isPresent){
            result.setMessage(new ErrorResponse(ServiceResult.NOTEXIST.toString()));
        } else{
            userRepository.deleteById(userId);
        }
        return result;
    }
}
