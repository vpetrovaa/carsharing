package com.solvd.carsharing.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
    private long revision;
    private String data;
    private LocalDateTime createdAt;

}
