package com.solvd.carsharing.kafka.config;

import com.solvd.carsharing.kafka.property.KfProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverOptions;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class KfConsumerConfig {

    private final KfProperties kfProperties;

    @Bean
    public ReceiverOptions<String, String> receiverOptions() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kfProperties.getPort());
        props.put(ConsumerConfig.GROUP_ID_CONFIG, kfProperties.getGroup());
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        ReceiverOptions<String, String> receiverOptions = ReceiverOptions.create(props);
        return receiverOptions.subscription(Collections.singleton(kfProperties.getTopic()))
                .addAssignListener(partitions -> log.info("onAssigned: " + partitions))
                .addRevokeListener(partitions -> log.info("onRevoked: " + partitions));
    }

    @Bean
    public KafkaReceiver<String, String> kafkaReceiver() {
        return KafkaReceiver.create(receiverOptions());
    }

}