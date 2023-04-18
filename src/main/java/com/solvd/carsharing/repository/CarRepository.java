package com.solvd.carsharing.repository;

import com.solvd.carsharing.aggregate.CarAggregate;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface CarRepository extends ReactiveMongoRepository<CarAggregate, String> {

    Mono<Boolean> existsByNumber(String number);

    Mono<CarAggregate> findByNumber(String number);

}
