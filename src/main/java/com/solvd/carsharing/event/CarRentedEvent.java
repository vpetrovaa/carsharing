package com.solvd.carsharing.event;

public class CarRentedEvent extends Event{

    @Override
    public String getName(){
        return "CarRented";
    }

}
