package com.shanep.controller;


import com.shanep.model.Result;
import com.shanep.model.Transportation;
import com.shanep.repositories.TransportationRepository;
import com.shanep.service.TransportationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="api/v1/transportations")
public class TransportationController {

    @Autowired
    TransportationRepository transportationRepository;

    @Autowired
    TransportationService transportationService;



    @GetMapping
    public Result retrieveTransportation(){
        Result result = transportationService.retrieveTransportationList();

        return result;
    }

    @GetMapping("/{transportation_id}")
    public Result retrieveTransportation(@PathVariable Integer transportation_id){

        Result result = transportationService.retrieveTransportation(transportation_id);

        return result;

    }

    @PostMapping
    public Result createTransportation(@ModelAttribute Transportation transportation) {

        Result result = transportationService.createTransportation(transportation);

        return result;

    }


//    @PutMapping("/{transportation_id}") //put은 전체수정을 하는것
//    public Result putTransportation(@ModelAttribute Transportation transportation, @PathVariable Integer transportation_id){
//        Result result = transportationService.updateTransportation(transportation,transportation_id);
//
//        return result;
//    }


    @PatchMapping("/{transportation_id}") //patch는 부분수정을 하는 것.
    public Result updateTransportation(@RequestBody Transportation transportation, @PathVariable Integer transportation_id ){
        Result result = transportationService.updateTransportation(transportation,transportation_id);

        return result;
    }

    @DeleteMapping("/{transportation_id}")
    public Result deleteTransportation(@PathVariable Integer transportation_id){

        Result result = transportationService.deleteTransportation(transportation_id);

        return result;
    }
}
