package com.solvd.carsharing.web.controller;

import com.solvd.carsharing.aggregate.CarAggregate;
import com.solvd.carsharing.command.CommandService;
import com.solvd.carsharing.domain.Car;
import com.solvd.carsharing.web.dto.CarDto;
import com.solvd.carsharing.web.mapper.CarMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/cars")
@RequiredArgsConstructor
public class CarController {

    private final CommandService commandService;
    private final CarMapper carMapper;

    @PostMapping
    public Mono<CarAggregate> create(@RequestBody @Validated CarDto carDto) {
        Car carMapped = carMapper.toEntity(carDto);
        return commandService.handle(carMapped);
    }

    @PutMapping("/{aggregateId}")
    public Mono<CarAggregate> updateNumber(@PathVariable String aggregateId,
                                           @RequestParam String number) {
        return commandService.handle(aggregateId, number);
    }

}
