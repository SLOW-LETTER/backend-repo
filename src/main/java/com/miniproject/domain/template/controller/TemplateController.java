package com.miniproject.domain.template.controller;

import com.miniproject.domain.template.dto.TemplateDto;
import com.miniproject.domain.template.repository.TemplateRepository;
import com.miniproject.domain.template.service.TemplateService;
import com.miniproject.global.entity.Result;
import com.miniproject.global.file.service.S3Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * @author : 김현진
 * @version : 1.0.0
 * @package : com.miniproject.domain.template.controller
 * @name : TemplateController
 * @create-date: 2022.09.06
 * @update-date :
 * @update-author : 000
 * @update-description :
 */
@RestController
@RequestMapping(value = "/api/v1/templates")
@RequiredArgsConstructor
@Tag(name = "Template", description = "template api 입니다.")
public class TemplateController {
    private final S3Service s3Service;

    @Autowired
    TemplateRepository repository;

    @Autowired
    TemplateService templateService;

    @Operation(summary = "template 전체 조회", description = "template을 전체 조회하는 api 입니다")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "template 전체 조회 성공"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = Error.class)))
    })
    @GetMapping
    public Result retrieveTemplateList() {
        Result result = templateService.retrieveTemplateList();
        return result;
    }

    @Operation(summary = "template 생성", description = "template 생성 api 입니다")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "template 생성 성공"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = Error.class)))
    })
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Result createTemplate(@ModelAttribute TemplateDto templateDto) throws IOException {
        String url = s3Service.uploadFile(templateDto.getFile(), "templates");
        templateDto.setUrl(url);
        Result result = templateService.createTemplate(templateDto);
        return result;
    }

    @Operation(summary = "template 삭제", description = "template 삭제 api 입니다")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "template 삭제 성공"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = Error.class)))
    })
    @DeleteMapping("/{id}")
    public Result deleteTemplate(@PathVariable int id) {
        Result result = templateService.deleteTemplate(id);
        return result;
    }
}
