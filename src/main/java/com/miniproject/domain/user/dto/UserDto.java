package com.miniproject.domain.user.dto;

import com.miniproject.domain.user.entity.Role;
import com.miniproject.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private Long id;
    private String email;

    private String name;
    private String password;
    private String phone;
    private Boolean is_deleted;
    private String withdraw_feedback;
    private String profile_image_url;
    private String bio;
    private Boolean is_checked_other_send;
    private Boolean is_checked_my_receive;
    private Boolean is_checked_my_send;
    private Boolean is_checked_other_receive;
    private Role role;

    /* DTO -> Entity */
//    public User toEntity() {
//        User user = User.builder()
//                .email(email)
//                .password(password)
//                .nickname(nickname)
//                .email(email)
//                .role(role.USER)
//                .build();
//        return user;
//    }
}
