package com.solvd.carsharing.kafka;

import com.solvd.carsharing.command.CommandService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.kafka.receiver.KafkaReceiver;

@Slf4j
@Component
@RequiredArgsConstructor
public class KfConsumerImpl implements KfConsumer{

    private final KafkaReceiver<String, String> kafkaReceiver;
    private final CommandService commandService;

    @Override
    @PostConstruct
    public void fetch() {
        this.kafkaReceiver.receive()
                .subscribe(r -> {
                    log.info("Car number is: {}", r.value());
                    commandService.handleRentCarCommand(r.value().substring(0, 6),
                            r.value().substring(6, r.value().length()));
                    r.receiverOffset().acknowledge();
                });
    }

}