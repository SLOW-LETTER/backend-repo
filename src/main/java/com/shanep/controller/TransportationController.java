package com.shanep.controller;

import com.shanep.model.Result;
import com.shanep.model.Transportation;
import com.shanep.repositories.TransportationRepository;
import com.shanep.service.TransportationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @package : com.shanep.controller
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
    public Result createTransportation(@ModelAttribute Transportation transportation) {
        Result result = transportationService.createTransportation(transportation);
        return result;
    }

    @PatchMapping("/{transportation_id}")
    public Result updateTransportation(@ModelAttribute Transportation transportation, @PathVariable Integer transportation_id) {
        Result result = transportationService.updateTransportation(transportation, transportation_id);
        return result;
    }

    @DeleteMapping("/{transportation_id}")
    public Result deleteTransportation(@PathVariable Integer transportation_id) {
        Result result = transportationService.deleteTransportation(transportation_id);
        return result;
    }
}
