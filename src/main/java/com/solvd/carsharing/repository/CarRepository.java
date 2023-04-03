package com.solvd.carsharing.repository;

import com.solvd.carsharing.aggregate.CarAggregate;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import reactor.core.publisher.Mono;

public interface CarRepository extends ReactiveCassandraRepository<CarAggregate, String> {

    Mono<CarAggregate> findByNumber(String number);

}
