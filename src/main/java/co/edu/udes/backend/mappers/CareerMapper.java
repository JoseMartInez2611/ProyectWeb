package co.edu.udes.backend.mappers;

import co.edu.udes.backend.dto.CareerDTO;
import co.edu.udes.backend.models.Career;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = {TeacherMapper.class})
public interface CareerMapper {
    CareerMapper INSTANCE = Mappers.getMapper(CareerMapper.class);

    Career toEntity(CareerDTO career);
    List<Career> toEntityList(List<CareerDTO> careers);

    CareerDTO toDto(Career career);
    List<CareerDTO> toDtoList(List<Career> careers);
}
