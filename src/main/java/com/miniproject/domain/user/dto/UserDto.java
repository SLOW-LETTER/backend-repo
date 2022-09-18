package com.miniproject.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
* @package : com.miniproject.domain.user.dto
* @name : UserDto
* @create-date: 2022-09-13
* @author : 박수현
* @version : 1.0.0
*
* @update-date :
* @update-author : 000
* @update-description :
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private Long user_id;
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
    private List<String> roles;
}
