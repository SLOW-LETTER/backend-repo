package com.miniproject.domain.letter.repository;

import com.miniproject.domain.letter.entity.Letter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @package : com.miniproject.domain.letter.repository;
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