package co.edu.udes.backend.services;


import co.edu.udes.backend.dto.EmployeeDTO;
import co.edu.udes.backend.mappers.EmployeeMapper;
import co.edu.udes.backend.models.Employee;
import co.edu.udes.backend.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    @Autowired
    private EmployeeMapper employeeMapper;

    public List<EmployeeDTO> getAll() {
        List<Employee> employees = employeeRepository.findAll();
        return employeeMapper.toDtoList(employees);
    }

    public EmployeeDTO getById(Long id) {
        return employeeMapper.toDto(employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id)));
    }

    public EmployeeDTO create(Employee employee) {
        return employeeMapper.toDto(employeeRepository.save(employee));
    }

    public EmployeeDTO update(Long id, Employee employee) {
        employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
        employee.setId(id);
        return employeeMapper.toDto(employeeRepository.save(employee));
    }

    public void delete(Long id) {
        employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
        employeeRepository.deleteById(id);
    }
}
