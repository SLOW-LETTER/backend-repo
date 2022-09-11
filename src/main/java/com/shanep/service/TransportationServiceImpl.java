package com.shanep.service;

import com.shanep.enumpkg.ServiceResult;

import com.shanep.model.ErrorResponse;
import com.shanep.model.Result;
import com.shanep.model.Transportation;
import com.shanep.repositories.TransportationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @package : com.shanep.service
 * @name : TransportationServiceImpl
 * @create-date: 2022.09.06
 * @author : 원우연
 * @version : 1.0.0
 *
 * @update-date :
 * @update-author : 000
 * @update-description :
 */
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
        Result result = new Result();
        result.setPayload(list);
        return result;
    }


    @Override
    public Result retrieveTransportation(int transportationId) {
        Optional<Transportation> optionTransportation = transportationRepository.findById(transportationId);
        Result result = new Result();
        if(optionTransportation.isPresent()){
            if(optionTransportation.get().is_deleted() == false) {
                result.setPayload(optionTransportation.get());
            }
        } else{ 
            result.setMessage(new ErrorResponse(ServiceResult.NOTEXIST.toString()));
        }
        return result;
    }


    @Override
    public Result updateTransportation(Transportation transportation, int transportationId) {
        Optional<Transportation> updateTransportation = transportationRepository.findById(transportationId);
        Result result = new Result();
        if(updateTransportation.isPresent()){
            if(transportation.getName() != null) {
                updateTransportation.get().setName(transportation.getName());
            }
            if(transportation.getVelocity() != 0){
                updateTransportation.get().setVelocity(transportation.getVelocity());
            }
            transportation= transportationRepository.save(updateTransportation.get());
            result.setPayload(transportation);
        } else{
            result.setMessage(new ErrorResponse(ServiceResult.NOTEXIST.toString()));
        }
        return result;
    }


    @Override
    public Result deleteTransportation(int transportationId) {
        Result result = new Result();
        boolean isPresent = transportationRepository.findById(transportationId).isPresent();
        if(!isPresent){
            result.setMessage(new ErrorResponse(ServiceResult.NOTEXIST.toString()));
        } else{
            transportationRepository.deleteById(transportationId);
        }
        return result;
    }
}
