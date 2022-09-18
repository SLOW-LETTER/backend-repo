package com.miniproject.domain.user.dto;

import com.miniproject.domain.user.entity.User;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author : 박수현
 * @version : 1.0.0
 * @package : com.miniproject.domain.user.dto
 * @name : UserDto
 * @create-date: 2022-09-13
 * @update-date :
 * @update-author : 000
 * @update-description :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UserDto {
    private String oldPassword;
    private String newPassword;
    private Long user_id;
    private String email;
    private String name;
    private String password;
    private String phone;
    private Boolean is_deleted;
    private String withdraw_feedback;
    private MultipartFile file;
    private String profile_image_url;
    private String bio;
    private Boolean is_checked_other_send;
    private Boolean is_checked_my_receive;
    private Boolean is_checked_my_send;
    private Boolean is_checked_other_receive;
    private List<String> roles;

    /* DTO -> Entity */
    public User toEntity() {
        User user = User.builder()
                .user_id(user_id)
                .email(email)
                .password(password)
                .name(name)
                .phone(phone)
                .is_deleted(is_deleted)
                .withdraw_feedback(withdraw_feedback)
                .profile_image_url(profile_image_url)
                .bio(bio)
                .is_checked_other_send(is_checked_other_send)
                .is_checked_my_receive(is_checked_my_receive)
                .is_checked_my_send(is_checked_my_send)
                .is_checked_other_receive(is_checked_other_receive)
                .roles(roles)
                .build();
        return user;
    }
}
