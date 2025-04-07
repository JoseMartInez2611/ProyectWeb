package co.edu.udes.backend.services;


import co.edu.udes.backend.dto.EmployeeDTO;
import co.edu.udes.backend.mappers.EmployeeMapper;
import co.edu.udes.backend.models.Employee;
import co.edu.udes.backend.repositories.EmployeeRepository;
import co.edu.udes.backend.utils.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    public List<EmployeeDTO> getAll() {
        return employeeRepository.findAll().stream()
                .map(employeeMapper::toDTO)
                .collect(Collectors.toList());
    }

    public EmployeeDTO getById(Long id) {
        Employee entity = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Entity not found with id: " + id));
        return employeeMapper.toDTO(entity);
    }

    public EmployeeDTO create(EmployeeDTO dto) {
        Employee entity = employeeMapper.toEntity(dto);
        return employeeMapper.toDTO(employeeRepository.save(entity));
    }

    public EmployeeDTO update(Long id, EmployeeDTO dto) {
        employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Entity not found with id: " + id));
        dto.setId(id);
        Employee updated = employeeRepository.save(employeeMapper.toEntity(dto));
        return employeeMapper.toDTO(updated);
    }

    public void delete(Long id) {
        Employee entity = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Entity not found with id: " + id));
        employeeRepository.delete(entity);
    }
}
