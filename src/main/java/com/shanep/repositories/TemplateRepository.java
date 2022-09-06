package com.shanep.repositories;

import com.shanep.model.Template;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TemplateRepository extends JpaRepository<Template, Integer> {
    public List<Template> findAllByOrderByIdDesc();
}
