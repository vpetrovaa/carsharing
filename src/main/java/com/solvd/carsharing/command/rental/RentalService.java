package com.solvd.carsharing.command.rental;

public interface RentalService {

    void callConfirmOrDenyMethod(String aggregateId, String value);

}
