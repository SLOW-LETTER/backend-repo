package com.miniproject.domain.letter.repository;

import com.miniproject.domain.letter.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @package : com.miniproject.domain.template.repository
 * @name : FileRepository
 * @create-date: 2022.09.17
 * @author : 김현진
 * @version : 1.0.0
 *
 * @update-date :
 * @update-author : 000
 * @update-description :
 */

@Repository
public interface FileRepository extends JpaRepository<File, Integer> {
    public List<File> findAllByOrderByIdDesc();
}
