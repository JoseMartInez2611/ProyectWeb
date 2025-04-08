package co.edu.udes.backend.mappers;

import co.edu.udes.backend.dto.CourseDTO;
import co.edu.udes.backend.models.Course;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
@NoArgsConstructor
public class CourseMapper {

    public Course toEntity(CourseDTO dto) {
        if (dto == null) {
            return null;
        }

        return Course.builder()
                .id(dto.getId())
                .name(dto.getName())
                .credits(dto.getCredits())
                .theoreticalHours(dto.getTheoreticalHours())
                .practicalHours(dto.getPracticalHours())
                .prerequisites(toEntityList(dto.getPrerequisites()))
                .objectives(dto.getObjectives())
                .content(dto.getContent())
                .competences(dto.getCompetences())
                .build();
    }

    public CourseDTO toDTO(Course entity) {
        if (entity == null) {
            return null;
        }

        return CourseDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .credits(entity.getCredits())
                .theoreticalHours(entity.getTheoreticalHours())
                .practicalHours(entity.getPracticalHours())
                .prerequisites(toDTOList(entity.getPrerequisites()))
                .objectives(entity.getObjectives())
                .content(entity.getContent())
                .competences(entity.getCompetences())
                .build();
    }

    public List<Course> toEntityList(List<CourseDTO> dtos) {
        if (dtos == null || dtos.isEmpty()) {
            return Collections.emptyList();
        }

        return dtos.stream()
                .map(this::toEntity)
                .toList();
    }

    public List<CourseDTO> toDTOList(List<Course> entities) {
        if (entities == null || entities.isEmpty()) {
            return Collections.emptyList();
        }

        return entities.stream()
                .map(this::toDTO)
                .toList();
    }
}
