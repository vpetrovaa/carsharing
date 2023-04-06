package com.solvd.carsharing.web.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EventDto {

    private String id;
    private String aggregateId;
    private String carNumber;
    private String eventType;
    private String data;
    private LocalDateTime createdAt;

}
