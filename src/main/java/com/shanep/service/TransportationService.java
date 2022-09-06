package com.shanep.service;

import com.shanep.model.Board;
import com.shanep.model.Result;
import com.shanep.model.Transportation;



public interface TransportationService {

    public Result createTransportation(Transportation transportation);
    public Result retrieveTransportationList();
    public Result retrieveTransportation(int transportationId);

    public Result updateTransportation(Transportation transportation, int transportationId);

    public Result deleteTransportation(int transportationId);
}
