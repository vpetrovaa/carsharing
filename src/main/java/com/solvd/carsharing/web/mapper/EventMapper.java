package com.solvd.carsharing.web.mapper;

import com.solvd.carsharing.event.Event;
import com.solvd.carsharing.web.dto.EventDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EventMapper {

    Event toEntity(EventDto eventDto);

    EventDto toDto(Event event);

}
