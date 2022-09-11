package com.shanep.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

/**
 * @package : com.shanep.config
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
