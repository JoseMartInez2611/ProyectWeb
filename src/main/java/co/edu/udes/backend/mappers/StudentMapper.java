package co.edu.udes.backend.mappers;

import co.edu.udes.backend.dto.StudentDTO;
import co.edu.udes.backend.models.AcademicRegistration;
import co.edu.udes.backend.models.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class StudentMapper {
    private MessageMapper messageMapper;
    private ReportMapper reportMapper;
    private BorrowMapper borrowMapper;
    private CommunicationMapper communicationMapper;
    private AttendanceMapper attendanceMapper;
    private AcademicRegistrationMapper academicRegistrationMapper;

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
                .attendance(attendanceMapper.toDTOList(student.getAttendance()))
                .academicRegistration(academicRegistrationMapper.toDTOList(student.getAcademicRegistration()))

                .message(messageMapper.toDTOList(student.getMessage()))
                .report(reportMapper.toDTOList(student.getReport()))
                .borrow(borrowMapper.toDTOList(student.getBorrow()))
                .communication(communicationMapper.toDTOList(student.getCommunication()))
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

                .attendance(attendanceMapper.toEntityList(studentDTO.getAttendance()))
                .academicRegistration(academicRegistrationMapper.toEntityList(studentDTO.getAcademicRegistration()))

                .message(messageMapper.toEntityList(studentDTO.getMessage()))
                .report(reportMapper.toEntityList(studentDTO.getReport()))
                .borrow(borrowMapper.toEntityList(studentDTO.getBorrow()))
                .communication(communicationMapper.toEntityList(studentDTO.getCommunication()))
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
