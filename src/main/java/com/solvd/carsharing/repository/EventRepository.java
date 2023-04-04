package com.solvd.carsharing.repository;

import com.solvd.carsharing.event.Event;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import reactor.core.publisher.Flux;

public interface EventRepository extends ReactiveCassandraRepository<Event, String> {

    @AllowFiltering
    Flux<Event> findAllByAggregateId(String aggregateId);

}
