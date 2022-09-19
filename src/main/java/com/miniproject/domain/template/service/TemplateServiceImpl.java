package com.miniproject.domain.template.service;

import com.miniproject.domain.template.dto.TemplateDto;
import com.miniproject.domain.template.entity.Template;
import com.miniproject.domain.template.repository.TemplateRepository;
import com.miniproject.global.entity.ErrorResponse;
import com.miniproject.global.entity.Result;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @package : com.miniproject.domain.template.service
 * @name : TemplateServiceImpl
 * @create-date: 2022.09.06
 * @author : 김현진
 * @version : 1.0.0
 *
 * @update-date :
 * @update-author : 000
 * @update-description :
 */
@Service
public class TemplateServiceImpl implements TemplateService {
    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(TemplateServiceImpl.class);

    @Autowired
    TemplateRepository repository;

    public Result createTemplate(TemplateDto templateDto) {
        Template template = new Template(templateDto.getFileName(), templateDto.getUrl());
        repository.save(template);
        Result result = new Result();
        result.setPayload(template);
        //result.setMessage("탬플릿 생성 성공");
        return result;
    }

    public Result retrieveTemplateList() {
        List<Template> list = repository.findAllByOrderByIdDesc();
        Result result = new Result();
        result.setPayload(list);
        //result.setMessage("탬플릿 전체 조회 성공");
        return result;
    }

    public Result deleteTemplate(int id) {
        Result result = new Result();
        boolean isPresent = repository.findById(id).isPresent();
        if (!isPresent) {
            //result.setError(new ErrorResponse(ServiceResult.NOTEXIST.toString()));
        } else {
            repository.deleteById(id);
        }
        //result.setMessage("탬플릿 삭제 성공");
        return result;
    }
}
