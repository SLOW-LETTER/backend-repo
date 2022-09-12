package com.miniproject.domain.transportation.service;

import com.miniproject.domain.transportation.entity.Transportation;
import com.miniproject.global.entity.Result;

public interface TransportationService {

    public Result createTransportation(Transportation transportation);
    public Result retrieveTransportationList();
    public Result retrieveTransportation(int transportationId);

    public Result updateTransportation(Transportation transportation, int transportationId);

    public Result deleteTransportation(int transportationId);
}
