package com.solvd.carsharing.web.dto;

import com.solvd.carsharing.domain.Car;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class CarDto {

    private String id;

    @NotEmpty(message = "Please enter the model")
    private String model;

    @NotEmpty(message = "Please enter the number")
    private String number;

    @NotEmpty(message = "Please enter the status")
    private Car.Status status;

}
