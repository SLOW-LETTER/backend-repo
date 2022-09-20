package com.miniproject.domain.letter.repository;

import com.miniproject.domain.letter.entity.Letter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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

@Repository
public interface LetterRepository extends JpaRepository<Letter, Integer> {
    public List<Letter> findAllByOrderByIdDesc();

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE letter SET is_deleted = true WHERE id = CAST(:id as INTEGER)", nativeQuery = true)
    void deleteById(@Param("id") int id);
}