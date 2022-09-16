package com.miniproject.domain.template.dto;

import lombok.Getter;
import lombok.Setter;
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
public class TemplateDto {
    private String fileName;
    private String url;
    private MultipartFile file;
}
