package co.edu.udes.backend.services;

import co.edu.udes.backend.dto.AcademicRegistrationDTO;
import co.edu.udes.backend.mappers.AcademicRegistrationMapper;
import co.edu.udes.backend.repositories.AcademicRegistrationRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AcademicRegistrationService {

    private final AcademicRegistrationRepository academicRegistrationRepository;
    private final AcademicRegistrationMapper academicRegistrationMapper;

    public List<AcademicRegistrationDTO> getAll() {
        return academicRegistrationRepository.findAll().stream()
                .map(academicRegistrationMapper::toDTO)
                .toList();
    }

    public AcademicRegistrationDTO getById(Long id) {
        return academicRegistrationMapper.toDTO(academicRegistrationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Academic registration not found with id: " + id)));
    }

    public AcademicRegistrationDTO create(AcademicRegistrationDTO dto) {
        return academicRegistrationMapper.toDTO(academicRegistrationRepository.save(academicRegistrationMapper.toEntity(dto)));
    }

    public AcademicRegistrationDTO update(Long id, AcademicRegistrationDTO dto) {
        academicRegistrationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Academic registration not found with id: " + id));
        dto.setId(id);
        return academicRegistrationMapper.toDTO(academicRegistrationRepository.save(academicRegistrationMapper.toEntity(dto)));
    }

    public void delete(Long id) {
        academicRegistrationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Academic registration not found with id: " + id));
        academicRegistrationRepository.deleteById(id);
    }
}
