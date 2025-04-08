package co.edu.udes.backend.mappers;

import co.edu.udes.backend.dto.AttendanceDTO;
import co.edu.udes.backend.models.Attendance;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AttendanceMapper {
    AttendanceMapper INSTANCE = Mappers.getMapper(AttendanceMapper.class);

    Attendance toEntity(AttendanceDTO attendance);
    List<Attendance> toEntityList(List<AttendanceDTO> attendances);

    AttendanceDTO toDto(Attendance attendance);
    List<AttendanceDTO> toDtoList(List<Attendance> attendances);
}
