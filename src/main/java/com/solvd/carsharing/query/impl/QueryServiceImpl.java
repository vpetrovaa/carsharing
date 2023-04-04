package com.solvd.carsharing.query.impl;

import com.solvd.carsharing.aggregate.CarAggregate;
import com.solvd.carsharing.event.Event;
import com.solvd.carsharing.query.FindAllCarsQuery;
import com.solvd.carsharing.query.FindAllEventsByAggregateIdQuery;
import com.solvd.carsharing.query.QueryService;
import com.solvd.carsharing.repository.CarRepository;
import com.solvd.carsharing.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Slf4j
@Service
@RequiredArgsConstructor
public class QueryServiceImpl implements QueryService {

    private final CarRepository carRepository;
    private final EventRepository eventRepository;

    @Override
    public Flux<CarAggregate> handle(FindAllCarsQuery query) {
        log.info("Find all cars query was called");
        return carRepository.findAll();
    }

    @Override
    public Flux<Event> handle(FindAllEventsByAggregateIdQuery query) {
        log.info("Find all events by aggregate id query was called");
        return eventRepository.findAllByAggregateId(query.aggregateId());
    }

}
