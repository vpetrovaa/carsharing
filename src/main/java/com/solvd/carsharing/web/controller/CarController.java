package com.solvd.carsharing.web.controller;

import com.solvd.carsharing.aggregate.CarAggregate;
import com.solvd.carsharing.command.CommandService;
import com.solvd.carsharing.domain.Car;
import com.solvd.carsharing.event.Event;
import com.solvd.carsharing.query.FindAllCarsQuery;
import com.solvd.carsharing.query.FindAllEventsByCarNumberQuery;
import com.solvd.carsharing.query.QueryService;
import com.solvd.carsharing.web.dto.CarAggregateDto;
import com.solvd.carsharing.web.dto.CarDto;
import com.solvd.carsharing.web.dto.EventDto;
import com.solvd.carsharing.web.mapper.CarMapper;
import com.solvd.carsharing.web.mapper.EventMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/cars")
@RequiredArgsConstructor
public class CarController {

    private final CommandService commandService;
    private final CarMapper carMapper;
    private final QueryService queryService;
    private final EventMapper eventMapper;

    @PostMapping
    public Mono<CarAggregateDto> create(@RequestBody @Validated CarDto carDto) {
        Car carMapped = carMapper.toEntity(carDto);
        Mono<CarAggregate> car = commandService.handle(carMapped);
        return car.map(carMapper::toDto);
    }

    @PutMapping("/{aggregateId}")
    public Mono<CarAggregateDto> updateNumber(@PathVariable String aggregateId,
                                           @RequestParam String number) {
        Mono<CarAggregate> car = commandService.handle(aggregateId, number);
        return car.map(carMapper::toDto);
    }

    @GetMapping("/events/{aggregateId}")
    public Flux<EventDto> findAllEventsByAggregateId(@PathVariable String aggregateId) {
        Flux<Event> events = queryService.handle(new FindAllEventsByCarNumberQuery(aggregateId));
        return events.map(eventMapper::toDto);
    }

    @GetMapping
    public Flux<CarAggregateDto> findAllCars() {
        Flux<CarAggregate> cars = queryService.handle(new FindAllCarsQuery());
        return cars.map(carMapper::toDto);
    }

}
