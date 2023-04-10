package com.solvd.carsharing.event;

import com.solvd.carsharing.command.CreateCarCommand;
import com.solvd.carsharing.command.RentCarCommand;
import com.solvd.carsharing.command.UpdateNumberCommand;

public interface EventService {

    Event create(CreateCarCommand command);

    Event update(UpdateNumberCommand command);

    Event rent(RentCarCommand command);

}
