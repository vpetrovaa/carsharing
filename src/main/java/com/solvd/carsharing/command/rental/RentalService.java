package com.solvd.carsharing.command.rental;

import reactor.core.publisher.Mono;

public interface RentalService {

    void callConfirmOrDenyMethod(String aggregateId, String value);

    Mono<String> getRenterName(String carNumber);

}
