package com.solvd.carsharing.repository;

import com.solvd.carsharing.aggregate.CarAggregate;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import reactor.core.publisher.Mono;

public interface CarRepository extends ReactiveCassandraRepository<CarAggregate, String> {

    @AllowFiltering
    Mono<Boolean> existsByNumber(String number);

    Mono<CarAggregate> findByNumber(String number);

}
