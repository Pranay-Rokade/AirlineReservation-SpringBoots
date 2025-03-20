package com.pranay.assignment1.controller;

import com.pranay.assignment1.dto.Flight;
import com.pranay.assignment1.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    FlightService flightService;

    @GetMapping("/")
    List<Flight> getAllFlights(@RequestParam(required = false, defaultValue = "asc", name = "sort") String sort){
        return flightService.getAllFlights(sort);
    }

    @PostMapping("/add")
    Flight createFlight(@RequestBody Flight flight){
        return flightService.addFlight(flight);
    }

    @GetMapping("/{id}")
    ResponseEntity<Flight> getFlightById(@PathVariable String id){
        System.out.println("controller "+id);

        try{
            return ResponseEntity.ok().body(flightService.getFlightById(id));
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/schedules")
    public List<Flight> getFlightSchedules( @PathVariable String id, @RequestParam(required = false) List<String> dates) {

        return flightService.getFlightSchedules(id, dates);
    }
}
