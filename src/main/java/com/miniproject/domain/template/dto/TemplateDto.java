package com.miniproject.domain.template.dto;

import com.miniproject.domain.template.entity.Template;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author : 김현진
 * @version : 1.0.0
 * @package : com.miniproject.domain.template.dto
 * @name : TemplateDto
 * @create-date: 2022.09.06
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

    @Schema(description = "템플릿 사진", nullable = false, hidden = true)
    private String url;

    @Schema(description = "템플릿 사진", nullable = false)
    private MultipartFile file;

    public Template toEntity() {
        Template template = Template.builder()
                .fileUrl(url)
                .build();
        return template;
    }
}
