package com.shanep.service;

import com.shanep.dto.TemplateDto;
import com.shanep.model.Result;

public interface TemplateService {

    public Result createTemplate(TemplateDto templateDto);

    // 탬플릿 전체조회
    public Result retrieveTemplateList();

    // 탬플릿 삭제
    public Result deleteTemplate(int id);
}
