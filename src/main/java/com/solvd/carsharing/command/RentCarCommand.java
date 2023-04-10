package com.solvd.carsharing.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentCarCommand {

    private String aggregateId;
    private String carNumber;

}
