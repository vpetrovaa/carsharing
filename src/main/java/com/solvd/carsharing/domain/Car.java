package com.solvd.carsharing.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car {

    private String model;
    private String number;
    private Status status;

    public enum Status{
        RENTED, FREE;
    }

}
