package co.edu.udes.backend.services;


import co.edu.udes.backend.dto.EmployeeDTO;
import co.edu.udes.backend.mappers.EmployeeMapper;
import co.edu.udes.backend.models.Employee;
import co.edu.udes.backend.repositories.EmployeeRepository;
import co.edu.udes.backend.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final RoleRepository roleRepository;
    @Autowired
    private EmployeeMapper employeeMapper;

    public List<EmployeeDTO> getAll() {
        List<Employee> employees = employeeRepository.findAll();
        return employeeMapper.toDtoList(employees);
    }

    public List<EmployeeDTO> createMultiple(List<Employee> employees) {
        employees = encriptPassword(employees);

        List<EmployeeDTO> savedEmployees = new ArrayList<EmployeeDTO>();
        for (Employee employee : employees) {
            savedEmployees.add(create(employee));
        }
        return savedEmployees;
    }

    public EmployeeDTO create(Employee employee) {

        employee.setRole(roleRepository.findById(4L)
                .orElseThrow(() -> new RuntimeException("Role not found")));
        employee.setEnable(true);
        employee.setAccountNonExpired(true);
        employee.setAccountNonLocked(true);
        employee.setCredentialsNonExpired(true);
        return employeeMapper.toDto(employeeRepository.save(employee));
    }

    public EmployeeDTO getById(Long id) {
        return employeeMapper.toDto(employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id)));
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

    public List<Employee> encriptPassword(List<Employee> employees) {
        for (Employee employee : employees) {
            employee.setPassword(new BCryptPasswordEncoder().encode(employee.getPassword()));
            System.out.println(employee.getPassword());
            System.out.println(new BCryptPasswordEncoder().encode(employee.getPassword()));
        }
        return employees;
    }
}
