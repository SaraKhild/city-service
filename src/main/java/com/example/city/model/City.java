package com.example.city.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class City {

    private String zip;
    private double lat;
    private double lng;
    private String city;
    private String stateId;
    private String stateNam;
    private double temperature;

}
