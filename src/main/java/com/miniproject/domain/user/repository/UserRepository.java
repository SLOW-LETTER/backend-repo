package com.miniproject.domain.user.repository;

import com.miniproject.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // public List<User> findAllByOrderByBoardnoDesc();
}
