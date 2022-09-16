package com.shanep.repositories;

import com.shanep.model.Letter;
import com.shanep.model.Template;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @package : com.shanep.repositories
 * @name : LetterRepository
 * @create-date: 2022.09.15
 * @author : 김현진
 * @version : 1.0.0
 *
 * @update-date :
 * @update-author : 000
 * @update-description :
 */


public interface LetterRepository extends JpaRepository<Letter, Integer> {
    public List<Letter> findAllByOrderByIdDesc();
}
