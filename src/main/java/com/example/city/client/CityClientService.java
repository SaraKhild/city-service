package com.example.city.client;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.city.model.City;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CityClientService {

    private final WebClient webClient;
    private static final String cityUrl = "http://localhost:3030/open-city-api/";

    public CityClientService() {
        this.webClient = WebClient.builder().baseUrl(cityUrl).build();
    }

    public Mono<City> getCity(String zipCode) {

        return this.webClient.get().uri(cityUrl + zipCode).retrieve().bodyToMono(City.class);

    }

    public Flux<City> addCities() {
        return webClient.get().uri(cityUrl).retrieve().bodyToFlux(City.class);

    }

}