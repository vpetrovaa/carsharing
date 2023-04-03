package com.solvd.carsharing.event.impl;

import com.solvd.carsharing.command.CreateCarCommand;
import com.solvd.carsharing.command.UpdateNumberCommand;
import com.solvd.carsharing.event.CarCreatedEvent;
import com.solvd.carsharing.event.Event;
import com.solvd.carsharing.event.EventService;
import com.solvd.carsharing.event.NumberUpdatedEvent;
import com.solvd.carsharing.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    @Override
    public Event create(CreateCarCommand command) {
        Event carCreatedEvent = new CarCreatedEvent();
        carCreatedEvent.setCreatedAt(LocalDateTime.now());
        carCreatedEvent.setAggregateId(command.getAggregateId());
        carCreatedEvent.setEventType(CarCreatedEvent.CAR_CREATED);
        carCreatedEvent.setData(command.getCar().toString());
        eventRepository.save(carCreatedEvent);
        return carCreatedEvent;
    }

    @Override
    public Event update(UpdateNumberCommand command) {
        Event numberUpdatedEvent = new NumberUpdatedEvent();
        numberUpdatedEvent.setCreatedAt(LocalDateTime.now());
        numberUpdatedEvent.setAggregateId(command.getAggregateId());
        numberUpdatedEvent.setEventType(NumberUpdatedEvent.CAR_UPDATED);
        numberUpdatedEvent.setData(command.getNumber());
        eventRepository.save(numberUpdatedEvent);
        return numberUpdatedEvent;
    }

}
