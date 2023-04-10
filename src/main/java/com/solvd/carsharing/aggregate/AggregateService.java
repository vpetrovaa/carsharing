package com.solvd.carsharing.aggregate;

import com.solvd.carsharing.domain.Car;
import com.solvd.carsharing.event.Event;
import reactor.core.publisher.Mono;

public interface AggregateService {

    void apply(Event event);

    void when(Event event);

    void validateEvent(Event event);

    Mono<CarAggregate> create(Event event, Car car);

    Mono<CarAggregate> update(Event event, String number);

    Mono<CarAggregate> findByNumber(String number);

    Mono<CarAggregate> rent(Event event, String number);

}
