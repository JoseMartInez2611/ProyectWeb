package co.edu.udes.backend.mappers;

import co.edu.udes.backend.dto.TeacherDTO;
import co.edu.udes.backend.models.Teacher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TeacherMapper {
    private GroupMapper groupMapper;
    private MessageMapper messageMapper;
    private ReportMapper reportMapper;
    private BorrowMapper borrowMapper;
    private CommunicationMapper communicationMapper;
    public TeacherDTO toDTO(Teacher teacher){
        return TeacherDTO.builder()
                //Datos Clase padre
                .id(teacher.getId())
                .email(teacher.getEmail())
                .firstName(teacher.getFirstName())
                .lastName(teacher.getLastName())
                .phone(teacher.getPhone())
                .userName(teacher.getUserName())
                .password(teacher.getPassword())
                .message(messageMapper.toDTOList(teacher.getMessage()))
                .report(reportMapper.toDTOList(teacher.getReport()))
                .borrow(borrowMapper.toDTOList(teacher.getBorrow()))
                .communication(communicationMapper.toDTOList(teacher.getCommunication()))

                //Datos Clase hija
                .speciality(teacher.getSpeciality())
                .groups(groupMapper.toDTOList(teacher.getGroups()))
                .build();
    }
    public Teacher toEntity(TeacherDTO teacherDTO){
        return Teacher.builder()
                //Datos Clase padre
                .id(teacherDTO.getId())
                .email(teacherDTO.getEmail())
                .firstName(teacherDTO.getFirstName())
                .lastName(teacherDTO.getLastName())
                .phone(teacherDTO.getPhone())
                .userName(teacherDTO.getUserName())
                .password(teacherDTO.getPassword())
                .message(messageMapper.toEntityList(teacherDTO.getMessage()))
                .report(reportMapper.toEntityList(teacherDTO.getReport()))
                .borrow(borrowMapper.toEntityList(teacherDTO.getBorrow()))
                .communication(communicationMapper.toEntityList(teacherDTO.getCommunication()))

                //Datos Clase hija
                .speciality(teacherDTO.getSpeciality())
                .groups(groupMapper.toEntityList(teacherDTO.getGroups()))
                .build();
    }
    public List<Teacher> toEntityList(List<TeacherDTO> dtos) {
        if (dtos == null || dtos.isEmpty()) {
            return Collections.emptyList();
        }

        return dtos.stream()
                .map(this::toEntity)
                .toList();
    }

    public List<TeacherDTO> toDTOList(List<Teacher> entities) {
        if (entities == null || entities.isEmpty()) {
            return Collections.emptyList();
        }

        return entities.stream()
                .map(this::toDTO)
                .toList();
    }
}
