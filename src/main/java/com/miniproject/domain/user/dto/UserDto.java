package com.miniproject.domain.user.dto;

import com.miniproject.domain.user.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

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
@Schema(description = "사용자 DTO")
public class UserDto {
    private String oldPassword;
    private String newPassword;
    private Long id;

    private String email;

    private String name;

    private String password;
    private String phone;

    @Builder.Default
    private Boolean isDeleted = Boolean.FALSE;
    private String withdrawFeedback;

    @Builder.Default
    private String profileImageUrl= "https://slowletter.s3.ap-northeast-2.amazonaws.com/users/default_profile.png";
    private String bio;

    @Builder.Default
    private Boolean isCheckedOtherSend = Boolean.TRUE;

    @Builder.Default
    private Boolean isCheckedMyReceive = Boolean.TRUE;

    @Builder.Default
    private Boolean isCheckedMySend = Boolean.TRUE;

    @Builder.Default
    private Boolean isCheckedOtherReceive = Boolean.TRUE;
    private List<String> roles;

    /* DTO -> Entity */
    public User toEntity() {
        User user = User.builder()
                .id(id)
                .email(email)
                .password(password)
                .name(name)
                .phone(phone)
                .isDeleted(isDeleted)
                .withdrawFeedback(withdrawFeedback)
                .profileImageUrl(profileImageUrl)
                .bio(bio)
                .isCheckedOtherReceive(isCheckedOtherReceive)
                .isCheckedMyReceive(isCheckedMyReceive)
                .isCheckedMySend(isCheckedMySend)
                .isCheckedOtherSend(isCheckedOtherSend)
                .roles(roles)
                .build();
        return user;
    }
}
