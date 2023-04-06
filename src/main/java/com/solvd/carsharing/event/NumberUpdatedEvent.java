package com.solvd.carsharing.event;

public class NumberUpdatedEvent extends Event{

    @Override
    public String getName(){
        return "CarUpdated";
    }

}
