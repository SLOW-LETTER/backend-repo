package com.shanep.controller;

import com.shanep.dto.TemplateDto;
import com.shanep.model.Result;
import com.shanep.repositories.TemplateRepository;
import com.shanep.service.S3Service;
import com.shanep.service.TemplateService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * @package : com.shanep.config
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