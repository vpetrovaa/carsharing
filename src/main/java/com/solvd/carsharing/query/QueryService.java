package com.solvd.carsharing.query;

import com.solvd.carsharing.aggregate.CarAggregate;
import com.solvd.carsharing.event.Event;
import reactor.core.publisher.Flux;

public interface QueryService {

    Flux<CarAggregate> handle(FindAllCarsQuery query);

    Flux<Event> handle(FindAllEventsByAggregateIdQuery query);

}
