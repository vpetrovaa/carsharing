package com.solvd.carsharing.aggregate;

import com.solvd.carsharing.domain.Car;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("cars")
@NoArgsConstructor
@AllArgsConstructor
public class CarAggregate{

    @Id
    private String id;
    private Long revision;
    private String model;
    private String number;
    private Car.Status status;

}
