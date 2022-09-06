package com.shanep.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class TemplateDto {
    private String fileName;
    private String url;
    private MultipartFile file;
}
