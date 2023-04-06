package com.solvd.carsharing.command;

import com.solvd.carsharing.domain.Car;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarCommand {

    private String aggregateId;
    private Car car;

}
