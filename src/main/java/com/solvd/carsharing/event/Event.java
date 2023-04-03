package com.solvd.carsharing.event;

import lombok.*;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.time.LocalDateTime;

@Data
@Table("events")
@NoArgsConstructor
@AllArgsConstructor
public class Event {

    @PrimaryKey
    private String id;
    private String aggregateId;
    private String eventType;
    private String aggregateType;
    private long revision;
    private String data;
    private LocalDateTime createdAt;

}
