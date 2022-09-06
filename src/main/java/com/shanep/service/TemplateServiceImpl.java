package com.shanep.service;

import com.shanep.dto.TemplateDto;
import com.shanep.enumpkg.ServiceResult;
import com.shanep.model.ErrorResponse;
import com.shanep.model.Template;
import com.shanep.model.Result;
import com.shanep.repositories.TemplateRepository;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TemplateServiceImpl implements TemplateService {
    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(TemplateServiceImpl.class);

    @Autowired
    TemplateRepository repository;

    /**
     * @param : 변수명 - 변수기능, 변수명 - 변수기능
     * @author : HYEON JIN
     * @method: 템플릿 생성 기능
     * @create-date: 2022.09.05
     */
    public Result createTemplate(TemplateDto templateDto) {
        Template template = new Template(templateDto.getFileName(), templateDto.getUrl());
        repository.save(template);
        Result result = new Result();
        result.setTemplates(template);
        result.setMessage("탬플릿 생성 성공");
        return result;
    }

    public Result retrieveTemplateList() {
        List<Template> list = repository.findAllByOrderByIdDesc();
        Result result = new Result();
        result.setTemplates(list);
        result.setMessage("탬플릿 전체 조회 성공");
        return result;
    }

    public Result deleteTemplate(int id) {
        Result result = new Result();
        boolean isPresent = repository.findById(id).isPresent();
        if (!isPresent) {
            result.setError(new ErrorResponse(ServiceResult.NOTEXIST.toString()));
        } else {
            repository.deleteById(id);
        }
        result.setMessage("탬플릿 삭제 성공");
        return result;
    }
}
