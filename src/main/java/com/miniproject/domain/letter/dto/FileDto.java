package com.miniproject.domain.letter.dto;

import com.miniproject.domain.letter.entity.File;
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
public class FileDto {
    private String url;
    private MultipartFile file;

    public File toEntity(){
        File file = File.builder()
                .fileUrl(url)
                .build();
        return file;
    }
}
