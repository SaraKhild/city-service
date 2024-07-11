package com.example.city.service;

import org.redisson.api.RMapReactive;
import org.redisson.api.RedissonReactiveClient;
import org.redisson.codec.TypedJsonJacksonCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.city.client.CityClientService;
import com.example.city.model.City;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CityService {

    private CityClientService cityClientService;
    private RMapReactive<String, City> cityMap;

    @Autowired
    public CityService(RedissonReactiveClient client, CityClientService cityclient) {

        this.cityClientService = cityclient;
        this.cityMap = client.getMap("city", new TypedJsonJacksonCodec(String.class, City.class));

    }

    public void addCity() {

         cityClientService.getAllCities()
                .collectList()
                .flatMapMany(Flux::fromIterable)
                .flatMap(city -> cityMap.fastPut(city.getZip(), city)
                        .onErrorResume(e -> {
                            // Log the error and continue
                            System.err.println("Failed to add city: " + e.getMessage());
                            return Mono.empty();
                        })).subscribe();
               

        // ************************Another Way**************************** */
        // this.cityClientService.getAllCities().collectList().map(l->l.stream().collect(Collectors.toMap(City::getZip,
        // Function.identity()))).flatMap(this.cityMap::putAll).subscribe();

    }

    public Flux<City> getAllCities() {

        return this.cityClientService.getAllCities();

    }

    public Mono<City> getCity(String zipCode) {

        return this.cityMap.get(zipCode).switchIfEmpty(this.cityClientService.getCity(zipCode)
                .flatMap(city -> this.cityMap.fastPut(zipCode, city).thenReturn(city)));

    }

}
