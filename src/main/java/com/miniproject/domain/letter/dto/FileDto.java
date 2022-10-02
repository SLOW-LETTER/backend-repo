package com.miniproject.domain.letter.dto;

import com.miniproject.domain.letter.entity.File;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @package : com.miniproject.domain.letter.dto;
 * @name : FileDto
 * @create-date: 2022.09.17
 * @author : 김현진
 * @version : 1.0.0
 *
 * @update-date :
 * @update-author : 000
 * @update-description :
 */

@Getter
@Setter
@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
//@Schema(description = "편지 파일 DTO")
public class FileDto {
    @Schema(description = "파일 첨부 사진", hidden = true)
    private String url;

    @Schema(description = "파일 첨부 사진")
    private MultipartFile file;

    public File toEntity(){
        File file = File.builder()
                .fileUrl(url)
                .build();
        return file;
    }
}