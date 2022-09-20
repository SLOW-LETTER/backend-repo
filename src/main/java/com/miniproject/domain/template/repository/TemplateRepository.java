package com.miniproject.domain.template.repository;

import com.miniproject.domain.template.entity.Template;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author : 김현진
 * @version : 1.0.0
 * @package : com.miniproject.domain.template.repository
 * @name : TemplateRepository
 * @create-date: 2022.09.15
 * @update-date :
 * @update-author : 000
 * @update-description :
 */
@Repository
public interface TemplateRepository extends JpaRepository<Template, Integer> {
    public List<Template> findAllByOrderByIdDesc();

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE template SET is_deleted = true WHERE id = CAST(:id as INTEGER)", nativeQuery = true)
    void deleteById(@Param("id") int id);
}
