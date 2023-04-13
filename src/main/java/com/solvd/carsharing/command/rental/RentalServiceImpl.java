package com.solvd.carsharing.command.rental;

import com.solvd.carsharing.kafka.property.KfProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class RentalServiceImpl implements RentalService {

    private final KfProperties properties;

    @Override
    public void callConfirmOrDenyMethod(String aggregateId, String value){
        WebClient webClient = WebClient.create("http://" + properties.getHost());
        Mono<String> confirm = webClient
                .get()
                .uri("/api/v1/rentals/" + value + "/" + aggregateId)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToMono(String.class);
    }

}
