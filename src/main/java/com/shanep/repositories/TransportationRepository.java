package com.shanep.repositories;

import com.shanep.model.Board;
import com.shanep.model.Transportation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @package : com.shanep.repositories
 * @name : TransportationRepository
 * @create-date: 2022.09.06
 * @author : 원우연
 * @version : 1.0.0
 *
 * @update-date :
 * @update-author : 000
 * @update-description :
 */
@Repository
public interface TransportationRepository extends JpaRepository<Transportation, Integer> {

    @Query(value = "select * from transportation where is_deleted =false",nativeQuery = true)
    public List<Transportation> findAllByOrderByTransportationIdDesc();



}
