package com.solvd.carsharing.event.impl;

import com.solvd.carsharing.command.CreateCarCommand;
import com.solvd.carsharing.command.RentCarCommand;
import com.solvd.carsharing.command.UpdateNumberCommand;
import com.solvd.carsharing.event.*;
import com.solvd.carsharing.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    @Override
    public Event create(CreateCarCommand command) {
        Event carCreatedEvent = new CarCreatedEvent();
        carCreatedEvent.setId(UUID.randomUUID().toString());
        carCreatedEvent.setCreatedAt(LocalDateTime.now());
        carCreatedEvent.setAggregateId(command.getAggregateId());
        carCreatedEvent.setCarNumber(command.getCar().getNumber());
        carCreatedEvent.setEventType(carCreatedEvent.getName());
        carCreatedEvent.setData(command.getCar().toString());
        eventRepository.save(carCreatedEvent);
        return carCreatedEvent;
    }

    @Override
    public Event update(UpdateNumberCommand command) {
        Event numberUpdatedEvent = new NumberUpdatedEvent();
        numberUpdatedEvent.setId(UUID.randomUUID().toString());
        numberUpdatedEvent.setCreatedAt(LocalDateTime.now());
        numberUpdatedEvent.setAggregateId(command.getAggregateId());
        numberUpdatedEvent.setEventType(numberUpdatedEvent.getName());
        numberUpdatedEvent.setData(command.getNumber());
        numberUpdatedEvent.setCarNumber(command.getNumber());
        eventRepository.save(numberUpdatedEvent);
        return numberUpdatedEvent;
    }

    @Override
    public Event rent(RentCarCommand command) {
        Event carRentedEvent = new CarRentedEvent();
        carRentedEvent.setId(UUID.randomUUID().toString());
        carRentedEvent.setCreatedAt(LocalDateTime.now());
        carRentedEvent.setAggregateId(command.getAggregateId());
        carRentedEvent.setEventType(carRentedEvent.getName());
        carRentedEvent.setData(command.getCarNumber());
        carRentedEvent.setCarNumber(command.getCarNumber());
        eventRepository.save(carRentedEvent);
        return carRentedEvent;
    }

}
