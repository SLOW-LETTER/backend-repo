package com.miniproject.domain.transportation.controller;

import com.miniproject.domain.transportation.dto.TransportationDto;
import com.miniproject.domain.transportation.repository.TransportationRepository;
import com.miniproject.domain.transportation.service.TransportationService;
import com.miniproject.global.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @package : com.miniproject.domain.transportation.controller
 * @name : TransportationController
 * @create-date: 2022.09.06
 * @author : 원우연
 * @version : 1.0.0
 *
 * @update-date :
 * @update-author : 000
 * @update-description :
 */

@Slf4j
@RestController
@RequestMapping(value = "api/v1/transportations")
public class TransportationController {

    @Autowired
    TransportationRepository transportationRepository;

    @Autowired
    TransportationService transportationService;



    @GetMapping
    public Result retrieveTransportation() {
        Result result = transportationService.retrieveTransportationList();
        return result;
    }

    @GetMapping("/{transportation_id}")
    public Result retrieveTransportation(@PathVariable Integer transportation_id) {
        Result result = transportationService.retrieveTransportation(transportation_id);
        return result;
    }

    @PostMapping
    public Result createTransportation(@ModelAttribute TransportationDto transportationdto) {
        Result result = transportationService.createTransportation(transportationdto);
        return result;
    }

    @PatchMapping("/{transportation_id}")
    public Result updateTransportation(@ModelAttribute TransportationDto transportationdto, @PathVariable Integer transportation_id) {
        Result result = transportationService.updateTransportation(transportationdto, transportation_id);
        return result;
    }

    @DeleteMapping("/{transportation_id}")
    public Result deleteTransportation(@PathVariable Integer transportation_id) {
        Result result = transportationService.deleteTransportation(transportation_id);
        return result;
    }
}
