package com.miniproject.domain.transportation.repository;

import com.miniproject.domain.transportation.entity.Transportation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TransportationRepository extends JpaRepository<Transportation, Integer> {

    public List<Transportation> findAllByOrderByTransportationIdDesc();


}
