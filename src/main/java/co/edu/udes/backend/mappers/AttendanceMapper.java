package co.edu.udes.backend.mappers;

import co.edu.udes.backend.dto.AttendanceDTO;
import co.edu.udes.backend.models.Attendance;

import java.util.Collections;
import java.util.List;

public class AttendanceMapper {

    private final LessonMapper lessonMapper = new LessonMapper();
    private final StudentMapper studentMapper = new StudentMapper();
    private final AbsenceJustificationMapper absenceJustificationMapper = new AbsenceJustificationMapper();

    public Attendance toEntity(AttendanceDTO dto) {
        if (dto == null) {
            return null;
        }

        return Attendance.builder()
                .id(dto.getId())
                .lesson(lessonMapper.toEntity(dto.getLesson()))
                .student(studentMapper.toEntity(dto.getStudent()))
                .assistance(dto.isAssistance())
                .date(dto.getDate())
                .justification(absenceJustificationMapper.toEntity(dto.getJustification()))
                .build();
    }

    public AttendanceDTO toDTO(Attendance entity) {
        if (entity == null) {
            return null;
        }

        return AttendanceDTO.builder()
                .id(entity.getId())
                .lesson(lessonMapper.toDTO(entity.getLesson()))
                .student(studentMapper.toDTO(entity.getStudent()))
                .assistance(entity.isAssistance())
                .date(entity.getDate())
                .justification(absenceJustificationMapper.toDTO(entity.getJustification()))
                .build();
    }

    public List<Attendance> toEntityList(List<AttendanceDTO> dtos) {
        if (dtos == null || dtos.isEmpty()) {
            return Collections.emptyList();
        }

        return dtos.stream()
                .map(this::toEntity)
                .toList();
    }

    public List<AttendanceDTO> toDTOList(List<Attendance> entities) {
        if (entities == null || entities.isEmpty()) {
            return Collections.emptyList();
        }

        return entities.stream()
                .map(this::toDTO)
                .toList();
    }
}
