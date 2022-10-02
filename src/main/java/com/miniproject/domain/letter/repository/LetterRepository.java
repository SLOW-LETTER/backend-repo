package com.miniproject.domain.letter.repository;

import com.miniproject.domain.letter.entity.Letter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
    public Optional<Letter> findAllByOrderByIdDesc();

    @Query(value = "select * from letter where sender_id=CAST(:senderId as int)", nativeQuery = true)
    public List<Letter> findAllByOrderBySenderIdDesc(@Param("senderId") Long senderId);

    @Query(value = "select * from letter where receiver_id=CAST(:receiverId as int)", nativeQuery = true)
    public List<Letter> findAllByOrderByReceiverIdDesc(@Param("receiverId") Long receiverId);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE letter SET is_deleted = true WHERE id = CAST(:id as INTEGER)", nativeQuery = true)
    void deleteById(@Param("id") int id);
}
