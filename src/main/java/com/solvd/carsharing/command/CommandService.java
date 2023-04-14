package com.solvd.carsharing.command;

import com.solvd.carsharing.aggregate.CarAggregate;
import com.solvd.carsharing.domain.Car;
import reactor.core.publisher.Mono;

public interface CommandService {

    Mono<CarAggregate> handleCreateCarCommand(Car car);

    Mono<CarAggregate> handleUpdateNumberCommand(String aggregateId, String number);

    Mono<CarAggregate> handleRentCarCommand(String number, String aggregateId);

}
