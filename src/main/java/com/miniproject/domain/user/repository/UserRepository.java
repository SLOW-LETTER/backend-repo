package com.miniproject.domain.user.repository;

import com.miniproject.domain.transportation.entity.Transportation;
import com.miniproject.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
* @package : com.miniproject.domain.user.repository
* @name : UserRepository
* @create-date: 2022-09-15
* @author : 박수현
* @version : 1.0.0
* 
* @update-date :
* @update-author : 000
* @update-description : 
*/
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "select * from user where is_deleted =false",nativeQuery = true)
    public List<User> findAllByOrderByUserIdDesc();

    Optional<User> findByEmail(String email);
}
