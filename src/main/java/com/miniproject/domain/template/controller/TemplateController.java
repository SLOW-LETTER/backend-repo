package com.miniproject.domain.template.controller;

import com.miniproject.domain.template.dto.TemplateDto;
import com.miniproject.domain.template.repository.TemplateRepository;
import com.miniproject.domain.template.service.TemplateService;
import com.miniproject.global.entity.Result;
import com.miniproject.global.file.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping(value="/api/v1/templates")
@RequiredArgsConstructor

public class TemplateController {
    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(TemplateController.class);
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
    public Result createTemplate(TemplateDto templateDto) throws IOException{
        String url = s3Service.uploadFile(templateDto.getFile());
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