package com.miniproject.domain.template.service;

import com.miniproject.domain.template.dto.TemplateDto;
import com.miniproject.global.entity.Result;

public interface TemplateService {

    public Result createTemplate(TemplateDto templateDto);

    // 탬플릿 전체조회
    public Result retrieveTemplateList();

    // 탬플릿 삭제
    public Result deleteTemplate(int id);
}
