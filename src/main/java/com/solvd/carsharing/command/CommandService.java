package com.solvd.carsharing.command;

import com.solvd.carsharing.aggregate.CarAggregate;
import com.solvd.carsharing.domain.Car;
import reactor.core.publisher.Mono;

public interface CommandService {

    Mono<CarAggregate> handle(Car car);

    Mono<CarAggregate> handle(String aggregateId, String number);

}
