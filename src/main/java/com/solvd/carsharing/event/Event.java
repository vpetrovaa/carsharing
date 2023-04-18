package com.solvd.carsharing.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document("car_events")
@NoArgsConstructor
@AllArgsConstructor
public class Event {

    @Id
    private String id;
    private String aggregateId;
    private String carNumber;
    private String eventType;
    private String data;
    private LocalDateTime createdAt;

    public String getName(){
        return "Global Event";
    }

}
