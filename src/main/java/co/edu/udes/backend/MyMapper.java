package co.edu.udes.backend;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel =  MappingConstants.ComponentModel.SPRING)
public interface MyMapper {

    @Mapping(source = "amountOfSeats", target = "numberOfSeats")
    @Mapping(source = "maxSpeed", target = "maximumSpeed")
    CarEntity toEntity(Car car);

    @InheritInverseConfiguration
    Car fromEntity(CarEntity entity);
}
