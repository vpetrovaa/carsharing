package com.solvd.carsharing.command.impl;

import com.solvd.carsharing.aggregate.AggregateService;
import com.solvd.carsharing.aggregate.CarAggregate;
import com.solvd.carsharing.command.CreateCarCommand;
import com.solvd.carsharing.command.CommandService;
import com.solvd.carsharing.command.UpdateNumberCommand;
import com.solvd.carsharing.domain.Car;
import com.solvd.carsharing.event.Event;
import com.solvd.carsharing.event.EventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommandServiceImpl implements CommandService {

    private final AggregateService aggregateService;
    private final EventService eventService;

    @Override
    public Mono<CarAggregate> handle(Car car) {
        CreateCarCommand command = new CreateCarCommand();
        command.setCar(car);
        String aggregateId = UUID.randomUUID().toString();
        command.setAggregateId(aggregateId);
        Event event = eventService.create(command);
        return aggregateService.create(event, command.getCar());
    }

    @Override
    public Mono<CarAggregate> handle(String aggregateId, String number) {
        UpdateNumberCommand command = new UpdateNumberCommand();
        command.setAggregateId(aggregateId);
        command.setNumber(number);
        Event event = eventService.update(command);
        return aggregateService.update(event, number);
    }

}
