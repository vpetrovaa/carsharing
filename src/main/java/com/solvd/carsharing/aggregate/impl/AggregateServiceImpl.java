package com.solvd.carsharing.aggregate.impl;

import com.solvd.carsharing.aggregate.AggregateService;
import com.solvd.carsharing.aggregate.CarAggregate;
import com.solvd.carsharing.domain.Car;
import com.solvd.carsharing.domain.exception.IllegalEventException;
import com.solvd.carsharing.domain.exception.ResourceAlreadyExistsException;
import com.solvd.carsharing.event.Event;
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
        return carRepository.existsByNumber(car.getNumber())
                .flatMap(isExist -> {
                    if (isExist) {
                        return Mono.error(new ResourceAlreadyExistsException("Car with number "
                                + car.getNumber() + " already exists"));
                    }
                    apply(event);
                    CarAggregate aggregate = new CarAggregate();
                    aggregate.setId(event.getAggregateId());
                    aggregate.setRevision(1L);
                    aggregate.setModel(car.getModel());
                    aggregate.setStatus(car.getStatus());
                    aggregate.setNumber(car.getNumber());
                    return carRepository.save(aggregate);
                });
    }

    @Override
    public Mono<CarAggregate> update(Event event, String number) {
        return carRepository.findById(event.getAggregateId())
                .map(aggregate -> {
                    apply(event);
                    aggregate.setRevision(aggregate.getRevision() + 1L);
                    aggregate.setNumber(number);
                    return aggregate;
                }).flatMap(carRepository::save);
    }


}
