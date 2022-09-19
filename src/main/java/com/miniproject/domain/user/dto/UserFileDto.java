package com.miniproject.domain.user.dto;

import com.miniproject.domain.user.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

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
public class UserFileDto {
    @Schema(description = "프로필 사진")
    private MultipartFile file;

    @Schema(description = "이름", example = "홍길동")
    private String name;

    @Schema(description = "전화번호", example = "010-1234-5678")
    private String phone;

    @Schema(description = "설명", example = "안녕하세요. 홍길동입니다.")
    private String bio;

    /* DTO -> Entity */
    public User toEntity() {
        User user = User.builder()
                .name(name)
                .phone(phone)
                .bio(bio)
                .build();
        return user;
    }
}
