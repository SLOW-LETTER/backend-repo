package com.shanep.repositories;

import com.shanep.model.Board;
import com.shanep.model.Transportation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TransportationRepository extends JpaRepository<Transportation, Integer> {

    public List<Transportation> findAllByOrderByTransportationIdDesc();


}
