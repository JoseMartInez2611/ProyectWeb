package co.edu.udes.backend.mappers;

import co.edu.udes.backend.dto.StudentDTO;
import co.edu.udes.backend.models.Student;

import java.util.Collections;
import java.util.List;

public class StudentMapper {
    private final MessageMapper messageMapper = new MessageMapper();
    private final ReportMapper reportMapper = new ReportMapper();
    private final BorrowMapper borrowMapper = new BorrowMapper();
    private final CommunicationMapper communicationMapper = new CommunicationMapper();

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

                .message(messageMapper.toDTO(student.getMessage()))
                .report(reportMapper.toDTO(student.getReport()))
                .borrow(borrowMapper.toDTO(student.getBorrow()))
                .communication(communicationMapper.toDTO(student.getCommunication()))
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

                .message(messageMapper.toEntity(studentDTO.getMessage()))
                .report(reportMapper.toEntity(studentDTO.getReport()))
                .borrow(borrowMapper.toEntity(studentDTO.getBorrow()))
                .communication(communicationMapper.toEntity(studentDTO.getCommunication()))
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
