package com.miniproject.domain.template.controller;

import com.miniproject.domain.template.dto.TemplateDto;
import com.miniproject.domain.template.repository.TemplateRepository;
import com.miniproject.domain.template.service.TemplateService;
import com.miniproject.global.entity.Result;
import com.miniproject.global.file.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * @package : com.miniproject.domain.template.controller
 * @name : TemplateController
 * @create-date: 2022.09.06
 * @author : 김현진
 * @version : 1.0.0
 *
 * @update-date :
 * @update-author : 000
 * @update-description :
 */

@RestController
@RequestMapping(value="/api/v1/templates")
@RequiredArgsConstructor
public class TemplateController {
    private final S3Service s3Service;

    @Autowired
    TemplateRepository repository;

    @Autowired
    TemplateService templateService;

    @GetMapping
    public Result retrieveTemplateList(){
        Result result = templateService.retrieveTemplateList();
        return result;
    }

    @PostMapping
    public Result createTemplate(@ModelAttribute TemplateDto templateDto) throws IOException{
        String url = s3Service.uploadFile(templateDto.getFile(), "templates");
        templateDto.setUrl(url);
        Result result = templateService.createTemplate(templateDto);
        return result;
    }

    @DeleteMapping("/{id}")
    public Result deleteTemplate(@PathVariable int id){
        Result result = templateService.deleteTemplate(id);
        return result;
    }
}
