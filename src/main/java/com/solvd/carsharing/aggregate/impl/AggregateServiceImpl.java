package com.solvd.carsharing.aggregate.impl;

import com.solvd.carsharing.aggregate.AggregateService;
import com.solvd.carsharing.aggregate.CarAggregate;
import com.solvd.carsharing.domain.Car;
import com.solvd.carsharing.domain.exception.IllegalEventException;
import com.solvd.carsharing.event.CarCreatedEvent;
import com.solvd.carsharing.event.Event;
import com.solvd.carsharing.event.NumberUpdatedEvent;
import com.solvd.carsharing.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class AggregateServiceImpl implements AggregateService {

    private final CarRepository carRepository;

    @Override
    public void apply(Event event) {
        validateEvent(event);
        when(event);
        event.setRevision(event.getRevision()+1L);
    }

    @Override
    public void when(Event event) {
        switch (event.getEventType()) {
            case "CarCreated" -> log.info("Car was created");
            case "CarUpdated" -> log.info("Car was updated");
            default -> throw new IllegalEventException("Exception in " + event.getEventType() + " type");
        }
    }

    @Override
    public void validateEvent(Event event) {
        if (Objects.isNull(event))
            throw new IllegalEventException("Exception in " + event.getEventType() + " type");
    }

    @Override
    public Mono<CarAggregate> create(Event event, Car car) {
        apply(event);
        CarAggregate aggregate = new CarAggregate();
        aggregate.setId(event.getAggregateId());
        aggregate.setRevision(event.getRevision());
        aggregate.setModel(car.getModel());
        aggregate.setStatus(car.getStatus());
        aggregate.setNumber(car.getNumber());
        return carRepository.save(aggregate);
    }

    @Override
    public Mono<CarAggregate> update(Event event, String number) {
        return carRepository.findById(event.getAggregateId())
                .map(aggregate -> {
                    event.setRevision(aggregate.getRevision());
                    apply(event);
                    aggregate.setRevision(event.getRevision());
                    aggregate.setNumber(number);
                    return aggregate;
                }).flatMap(carRepository::save);
    }


}
