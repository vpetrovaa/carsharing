package com.solvd.carsharing.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateNumberCommand {

    private String aggregateId;
    private String number;

}
