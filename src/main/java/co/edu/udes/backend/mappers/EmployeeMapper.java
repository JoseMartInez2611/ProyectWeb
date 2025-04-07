package co.edu.udes.backend.mappers;

import co.edu.udes.backend.dto.EmployeeDTO;
import co.edu.udes.backend.models.Employee;

import java.util.Collections;
import java.util.List;

public class EmployeeMapper {


    private final BorrowMapper borrowMapper = new BorrowMapper();

    public  EmployeeDTO toDTO(Employee employee) {
        return EmployeeDTO.builder()
                .id(employee.getId())
                .email(employee.getEmail())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .phone(employee.getPhone())
                .userName(employee.getUserName())
                .password(employee.getPassword())

                //Datos Clase hija
                .workSpace(employee.getWorkSpace())
                .borrow(borrowMapper.toDTO(employee.getBorrow()))
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

                //Datos Clase hija
                .workSpace(EmployeeDTO.getWorkSpace())
                .borrow(borrowMapper.toEntity(EmployeeDTO.getBorrow()))
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
