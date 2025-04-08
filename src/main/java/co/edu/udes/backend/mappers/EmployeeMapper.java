package co.edu.udes.backend.mappers;

import co.edu.udes.backend.dto.EmployeeDTO;
import co.edu.udes.backend.models.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class EmployeeMapper {

    private BorrowMapper borrowMapper;
    private MessageMapper messageMapper;
    private ReportMapper reportMapper;
    private CommunicationMapper communicationMapper;

    public  EmployeeDTO toDTO(Employee employee) {
        return EmployeeDTO.builder()
                .id(employee.getId())
                .email(employee.getEmail())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .phone(employee.getPhone())
                .userName(employee.getUserName())
                .password(employee.getPassword())
                .message(messageMapper.toDTOList(employee.getMessage()))
                .report(reportMapper.toDTOList(employee.getReport()))
                .borrow(borrowMapper.toDTOList(employee.getBorrow()))
                .communication(communicationMapper.toDTOList(employee.getCommunication()))

                //Datos Clase hija
                .workSpace(employee.getWorkSpace())
                .build();
    }

    public Employee toEntity(EmployeeDTO EmployeeDTO) {
        return Employee.builder()
                //Datos Clase padre
                .id(EmployeeDTO.getId())
                .firstName(EmployeeDTO.getFirstName())
                .lastName(EmployeeDTO.getLastName())
                .phone(EmployeeDTO.getPhone())
                .email(EmployeeDTO.getEmail())
                .userName(EmployeeDTO.getUserName())
                .password(EmployeeDTO.getPassword())
                .message(messageMapper.toEntityList(EmployeeDTO.getMessage()))
                .report(reportMapper.toEntityList(EmployeeDTO.getReport()))
                .borrow(borrowMapper.toEntityList(EmployeeDTO.getBorrow()))
                .communication(communicationMapper.toEntityList(EmployeeDTO.getCommunication()))


                //Datos Clase hija
                .workSpace(EmployeeDTO.getWorkSpace())
                .build();
    }

    public List<Employee> toEntityList(List<EmployeeDTO> dtos) {
        if (dtos == null || dtos.isEmpty()) {
            return Collections.emptyList();
        }

        return dtos.stream()
                .map(this::toEntity)
                .toList();
    }

    public List<EmployeeDTO> toDTOList(List<Employee> entities) {
        if (entities == null || entities.isEmpty()) {
            return Collections.emptyList();
        }

        return entities.stream()
                .map(this::toDTO)
                .toList();
    }

}
