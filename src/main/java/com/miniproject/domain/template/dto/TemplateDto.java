package com.miniproject.domain.template.dto;

import com.miniproject.domain.template.entity.Template;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @package : com.miniproject.domain.template.dto
 * @name : TemplateDto
 * @create-date: 2022.09.06
 * @author : 김현진
 * @version : 1.0.0
 *
 * @update-date :
 * @update-author : 000
 * @update-description :
 */

@Getter
@Setter
@Builder
@AllArgsConstructor
@Data
@NoArgsConstructor
public class TemplateDto {
    private String url;
    private MultipartFile file;

    public Template toEntity() {
       Template template = Template.builder()
               .fileUrl(url)
               .build();
       return template;
    }
}
