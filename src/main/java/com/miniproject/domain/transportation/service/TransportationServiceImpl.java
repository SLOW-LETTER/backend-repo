package com.miniproject.domain.transportation.service;

import com.miniproject.domain.transportation.entity.Transportation;
import com.miniproject.domain.transportation.repository.TransportationRepository;
import com.miniproject.global.entity.ErrorResponse;
import com.miniproject.global.entity.Result;
import com.miniproject.global.enumpkg.ServiceResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TransportationServiceImpl implements TransportationService {

    @Autowired
    TransportationRepository transportationRepository;
    @Override //운송수단 생성 메소드
    public Result createTransportation(Transportation transportation) {
        transportation = transportationRepository.save(transportation);
        Result result = new Result();
        result.setPayload(transportation);
        return result;
    }

    @Override
    public Result retrieveTransportationList() {
        List<Transportation> list = transportationRepository.findAllByOrderByTransportationIdDesc();
        System.out.println("리스트의 수?"+list);
        Result result = new Result();
        result.setPayload(list);
        return result;
    }

    @Override
    public Result retrieveTransportation(int transportationId) {
        Optional<Transportation> optionTransportation = transportationRepository.findById(transportationId);
        Result result = new Result();
        if(optionTransportation.isPresent()){
            result.setPayload(optionTransportation.get());
        } else{ 
            result.setError(new ErrorResponse(ServiceResult.NOTEXIST.toString()));
        }
        return result;
    }

    @Transactional
    @Override
    public Result updateTransportation(Transportation transportation,int transportationId){
        System.out.println("로그를 찍어봅시당."+transportationId+"\t겟아이디:"+transportation.getTransportationId());

        Optional<Transportation> search = transportationRepository.findById(transportationId);
        System.out.println(search.get());
        Result result = new Result();

        if(search.isPresent()){
            System.out.println("뭘까 이건>"+search.isPresent());
             transportation.setVelocity(transportation.getVelocity());
             transportation.setName(transportation.getName());
            result.setPayload(transportation);
        } else{
            result.setError(new ErrorResponse(ServiceResult.NOTEXIST.toString()));
        }
        return result;
    }

    @Override
    public Result deleteTransportation(int transportationId) {
        Result result = new Result();
        boolean isPresent = transportationRepository.findById(transportationId).isPresent();
        if(!isPresent){
            result.setError(new ErrorResponse(ServiceResult.NOTEXIST.toString()));
        } else{
            transportationRepository.deleteById(transportationId);
        }
        return result;
    }
}
