package co.edu.udes.backend.mappers;

import co.edu.udes.backend.dto.StudentDTO;
import co.edu.udes.backend.models.Student;

import java.util.Collections;
import java.util.List;

public class StudentMapper {
    public StudentDTO toDTO(Student student){
        return StudentDTO.builder()
                //Datos Clase padre
                .id(student.getId())
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .phone(student.getPhone())
                .email(student.getEmail())
                .userName(student.getUserName())
                .password(student.getPassword())

                //Datos Clase hija
                .semester(student.getSemester())
                .code(student.getCode())
                .dateBirth(student.getDateBirth())
                .address(student.getAddress())
                .career(student.getCareer())

                .message(MessageMapper.toDTO(student.getMessage()))
                .report(ReportMapper.toDTO(student.getReport()))
                .borrow(BorrowMapper.toDTO(student.getBorrow()))
                .communication(CommunicationMapper.toDTO(student.getCommunication()))
                .build();
    }

    public Student toEntity(StudentDTO studentDTO) {
        return Student.builder()
                //Datos Clase padre
                .id(studentDTO.getId())
                .firstName(studentDTO.getFirstName())
                .lastName(studentDTO.getLastName())
                .phone(studentDTO.getPhone())
                .email(studentDTO.getEmail())
                .userName(studentDTO.getUserName())
                .password(studentDTO.getPassword())

                //Datos Clase hija
                .semester(studentDTO.getSemester())
                .code(studentDTO.getCode())
                .dateBirth(studentDTO.getDateBirth())
                .address(studentDTO.getAddress())
                .career(studentDTO.getCareer())

                .message(MessageMapper.toEntity(studentDTO.getMessage()))
                .report(ReportMapper.toEntity(studentDTO.getReport()))
                .borrow(BorrowMapper.toEntity(studentDTO.getBorrow()))
                .communication(CommunicationMapper.toEntity(studentDTO.getCommunication()))
                .build();
    }

    public List<Student> toEntityList(List<StudentDTO> dtos) {
        if (dtos == null || dtos.isEmpty()) {
            return Collections.emptyList();
        }

        return dtos.stream()
                .map(this::toEntity)
                .toList();
    }

    public List<StudentDTO> toDTOList(List<Student> entities) {
        if (entities == null || entities.isEmpty()) {
            return Collections.emptyList();
        }

        return entities.stream()
                .map(this::toDTO)
                .toList();
    }
}
