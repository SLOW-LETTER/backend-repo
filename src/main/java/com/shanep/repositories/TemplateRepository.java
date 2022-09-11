package com.shanep.repositories;

import com.shanep.model.Template;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @package : com.shanep.config
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
