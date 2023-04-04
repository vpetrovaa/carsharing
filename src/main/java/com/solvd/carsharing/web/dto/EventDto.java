package com.solvd.carsharing.web.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EventDto {

    private String id;
    private String aggregateId;
    private String eventType;
    private long revision;
    private String data;
    private LocalDateTime createdAt;

}
