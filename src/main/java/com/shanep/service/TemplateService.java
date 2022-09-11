package com.shanep.service;

import com.shanep.dto.TemplateDto;
import com.shanep.model.Result;

/**
 * @package : com.shanep.config
 * @name : TemplateService
 * @create-date: 2022.09.06
 * @author : 김현진
 * @version : 1.0.0
 *
 * @update-date :
 * @update-author : 000
 * @update-description :
 */


public interface TemplateService {

    public Result createTemplate(TemplateDto templateDto);

    // 탬플릿 전체조회
    public Result retrieveTemplateList();

    // 탬플릿 삭제
    public Result deleteTemplate(int id);
}
