package com.miniproject.domain.user.repository;

import com.miniproject.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author : 박수현
 * @version : 1.0.0
 * @package : com.miniproject.domain.user.repository
 * @name : UserRepository
 * @create-date: 2022-09-15
 * @update-date :
 * @update-author : 000
 * @update-description :
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "select * from users where is_deleted =false", nativeQuery = true)
    public List<User> findAllByOrderByUserIdDesc();

    @Query(value = "select * from users where email=CAST(:email as varchar)", nativeQuery = true)
    Optional<User> findByEmail(@Param("email") String email);

    @Query(value = "select * from users where is_deleted =false and email=CAST(:email as varchar)", nativeQuery = true)
    Optional<User> findByEmailIsNotDeleted(@Param("email") String email);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE users SET is_deleted = true, withdraw_feedback = CAST(:withdrawFeedback as varchar) WHERE email = CAST(:email as varchar)", nativeQuery = true)
    void deletedUserByEmail(@Param("email") String email, @Param("withdrawFeedback") String withdrawFeedback);
}
