package com.solvd.carsharing.repository;

import com.solvd.carsharing.event.Event;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;

public interface EventRepository extends ReactiveCassandraRepository<Event, String> {

}
