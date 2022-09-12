package com.miniproject.domain.transportation.service;

import com.miniproject.domain.transportation.entity.Transportation;
import com.miniproject.global.entity.Result;

/**
 * @package : com.miniproject.domain.transportation.service
 * @name : TransportationService
 * @create-date: 2022.09.06
 * @author : 원우연
 * @version : 1.0.0
 *
 * @update-date :
 * @update-author : 000
 * @update-description :
 */
public interface TransportationService {

    public Result createTransportation(Transportation transportation);
    public Result retrieveTransportationList();
    public Result retrieveTransportation(int transportationId);
    Result updateTransportation(Transportation transportation, int transportationId);
    public Result deleteTransportation(int transportationId);
}
