package com.solvd.carsharing.web.mapper;

import com.solvd.carsharing.domain.Car;
import com.solvd.carsharing.web.dto.CarDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CarMapper {

    Car toEntity(CarDto carDto);

}
