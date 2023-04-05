package com.solvd.carsharing.event;

public class CarCreatedEvent extends Event{

    @Override
    public String getName(){
        return "CarCreated";
    }

}
