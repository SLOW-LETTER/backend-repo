package com.miniproject.domain.template.repository;

import com.miniproject.domain.template.entity.Template;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @package : com.miniproject.domain.template.repository
 * @name : TemplateRepository
 * @create-date: 2022.09.06
 * @author : 김현진
 * @version : 1.0.0
 *
 * @update-date :
 * @update-author : 000
 * @update-description :
 */
@Repository
public interface TemplateRepository extends JpaRepository<Template, Integer> {
    public List<Template> findAllByOrderByIdDesc();
}
