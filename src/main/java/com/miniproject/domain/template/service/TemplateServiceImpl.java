package com.miniproject.domain.template.service;

import com.miniproject.domain.template.dto.TemplateDto;
import com.miniproject.domain.template.entity.Template;
import com.miniproject.domain.template.repository.TemplateRepository;
import com.miniproject.global.entity.Result;
import com.miniproject.global.enumpkg.ErrorCode;
import com.miniproject.global.error.service.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : 김현진
 * @version : 1.0.0
 * @package : com.miniproject.domain.template.service
 * @name : TemplateServiceImpl
 * @create-date: 2022.09.06
 * @update-date :
 * @update-author : 000
 * @update-description :
 */
@Service
@Slf4j
public class TemplateServiceImpl implements TemplateService {
    @Autowired
    TemplateRepository repository;

    public Result createTemplate(TemplateDto templateDto) {
        Template template = templateDto.toEntity();
        repository.save(template);
        Result result = new Result();
        result.setPayload(template);
        return result;
    }

    public Result retrieveTemplateList() {
        List<Template> list = repository.findAllByOrderByIdDesc();
        Result result = new Result();
        result.setPayload(list);
        return result;
    }

    public Result deleteTemplate(int id) {
        Result result = new Result();
        boolean isPresent = repository.findById(id).isPresent();
        if (!isPresent) {
            result.setMessage(ErrorCode.PA02);
            throw new CustomException(result);
        } else {
            repository.deleteById(id);
        }
        return result;
    }
}
