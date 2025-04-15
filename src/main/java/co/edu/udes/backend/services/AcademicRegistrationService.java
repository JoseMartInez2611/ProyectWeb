package co.edu.udes.backend.services;

import co.edu.udes.backend.dto.AcademicRegistrationDTO;
import co.edu.udes.backend.mappers.AcademicRegistrationMapper;
import co.edu.udes.backend.models.AcademicRegistration;
import co.edu.udes.backend.repositories.AcademicRegistrationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AcademicRegistrationService {

    private final AcademicRegistrationRepository academicRegistrationRepository;
    @Autowired
    private AcademicRegistrationMapper academicRegistrationMapper;

    public List<AcademicRegistrationDTO> getAll() {
        List<AcademicRegistration> academicRegistrations = academicRegistrationRepository.findAll();
        return academicRegistrationMapper.toDtoList(academicRegistrations);
    }

    public AcademicRegistrationDTO getById(Long id) {
        return academicRegistrationMapper.toDto(academicRegistrationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Academic registration not found with id: " + id)));
    }

    public AcademicRegistrationDTO create(AcademicRegistration academicRegistration) {
        return academicRegistrationMapper.toDto(academicRegistrationRepository.save(academicRegistration));
    }

    public List<AcademicRegistrationDTO> createMultiple(List<AcademicRegistration> academicRegistrations) {
        return academicRegistrationMapper.toDtoList(
                academicRegistrationRepository.saveAll(academicRegistrations)
        );
    }

    public AcademicRegistrationDTO update(Long id, AcademicRegistration academicRegistration) {
        academicRegistrationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Academic registration not found with id: " + id));
        academicRegistration.setId(id);
        return academicRegistrationMapper.toDto(academicRegistrationRepository.save(academicRegistration));
    }

    public void delete(Long id) {
        academicRegistrationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Academic registration not found with id: " + id));
        academicRegistrationRepository.deleteById(id);
    }
}
