package co.edu.udes.backend.mappers;

import co.edu.udes.backend.dto.TeacherDTO;
import co.edu.udes.backend.models.Career;
import co.edu.udes.backend.models.Teacher;
import co.edu.udes.backend.models.inheritance.ProfileU;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring",
        uses = {
                MessageMapper.class,
                ReportMapper.class,
                BorrowMapper.class,
                CommunicationMapper.class,
                ProfileUMapper.class
        }
)public interface TeacherMapper {

    @Mapping(source = "careerIds", target = "careers", qualifiedByName = "mapIdsToCareers")
    Teacher toEntity(TeacherDTO teacher);
    List<Teacher> toEntityList(List<TeacherDTO> teachers);

    @Mapping(source = "careers", target = "careerIds", qualifiedByName = "mapCareersToIds")
    TeacherDTO toDto(Teacher teacher);
    List<TeacherDTO> toDtoList(List<Teacher> teachers);

    @Named("mapIdsToCareers")
    static List<Career> mapIdsToCareers(List<Long> ids) {
        if (ids == null) return null;
        List<Career> careers = new ArrayList<>();
        for (Long id : ids) {
            Career career = new Career();
            career.setId(id);
            careers.add(career);
        }
        return careers;
    }

    @Named("mapCareersToIds")
    static List<Long> mapCareersToIds(List<Career> careers) {
        if (careers == null) return null;
        return careers.stream()
                .map(Career::getId)
                .toList();
    }
}
