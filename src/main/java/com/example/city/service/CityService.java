package com.example.city.service;

import org.redisson.api.RMapReactive;
import org.redisson.api.RedissonReactiveClient;
import org.redisson.codec.TypedJsonJacksonCodec;
import org.springframework.stereotype.Service;

import com.example.city.client.CityClientService;
import com.example.city.model.City;

import reactor.core.publisher.Mono;

@Service
public class CityService {

    private CityClientService cityClientService;
    private RMapReactive<String, City> cityMap;

    public CityService(RedissonReactiveClient client) {
        this.cityMap = client.getMap("city", new TypedJsonJacksonCodec(String.class, City.class));
    }

    public Mono<Void> addCity() {
        return this.cityClientService.addCities().collectList()
                .map(items -> items.stream().map(item -> this.cityMap.fastPut(item.getZip(),
                        item)))
                .then();
        // ************************Another Way**************************** */
        // return this.cityClientService.addCities().collectList()
        // .map(list -> list.stream().collect(Collectors.toMap(City::getZip,
        // Function.identity())))
        // .flatMap(cityMap -> {
        // cityMap.forEach((zip, city) -> this.cityMap.fastPut(zip, city));
        // return Mono.empty();
        // })
        // .then();

    }

    public Mono<City> getCity(String zipCode) {

        return this.cityMap.get(zipCode).switchIfEmpty(this.cityClientService.getCity(zipCode)
                .flatMap(city -> this.cityMap.fastPut(zipCode, city).thenReturn(city)));
    }

}
