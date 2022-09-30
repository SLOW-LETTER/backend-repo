package com.miniproject.domain.letter.repository;

import com.miniproject.domain.letter.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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

    @Query(value = "select * from file where letter_id=CAST(:letterId as int)", nativeQuery = true)
    public Optional<File> findAllByOrderByLetterIdDesc(@Param("letterId") int letterId);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE file SET is_deleted = true WHERE id = CAST(:id as INTEGER)", nativeQuery = true)
    void deleteById(@Param("id") int id);
}
