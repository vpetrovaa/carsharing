package com.solvd.carsharing.web.dto;

import com.solvd.carsharing.domain.Car;
import lombok.Data;

@Data
public class CarAggregateDto {

    private String id;
    private Long revision;
    private String model;
    private String number;
    private Car.Status status;

}
