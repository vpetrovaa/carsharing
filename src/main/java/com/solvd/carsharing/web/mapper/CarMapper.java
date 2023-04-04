package com.solvd.carsharing.web.mapper;

import com.solvd.carsharing.aggregate.CarAggregate;
import com.solvd.carsharing.domain.Car;
import com.solvd.carsharing.web.dto.CarAggregateDto;
import com.solvd.carsharing.web.dto.CarDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CarMapper {

    Car toEntity(CarDto carDto);

    CarAggregate toEntity(CarAggregateDto carAggregateDto);

    CarAggregateDto toDto(CarAggregate carAggregate);

}
