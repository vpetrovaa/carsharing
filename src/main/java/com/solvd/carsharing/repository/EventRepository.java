package com.solvd.carsharing.repository;

import com.solvd.carsharing.event.Event;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface EventRepository extends ReactiveMongoRepository<Event, String> {

    Flux<Event> findAllByCarNumber(String number);

}
