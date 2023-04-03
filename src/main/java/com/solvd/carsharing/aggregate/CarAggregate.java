package com.solvd.carsharing.aggregate;

import com.solvd.carsharing.domain.Car;
import lombok.*;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Data
@Table("cars")
@NoArgsConstructor
@AllArgsConstructor
public class CarAggregate{

    @PrimaryKey
    private String id;
    private Long revision;
    private String model;
    private String number;
    private Car.Status status;

}
