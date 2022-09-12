package com.miniproject.domain.template.repository;

import com.miniproject.domain.template.entity.Template;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TemplateRepository extends JpaRepository<Template, Integer> {
    public List<Template> findAllByOrderByIdDesc();
}
