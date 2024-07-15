package com.example.city.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.city.model.City;
import com.example.city.service.CityService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/city")
public class CityController {

    private CityService cityService;

    @Autowired
    public CityController(CityService cityService) {

        this.cityService = cityService;
    }

    @PostMapping
    public Mono<String> addCities() {

        return this.cityService.addCity().thenReturn("Success");

    }

    @GetMapping
    public Flux<City> all() {

        return this.cityService.getAllCities();

    }

    @GetMapping("{zipCode}")
    public Mono<City> getCityByZipCode(@PathVariable String zipCode) {

        return this.cityService.getCity(zipCode);
        
    }

}
