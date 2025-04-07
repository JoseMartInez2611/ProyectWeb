package co.edu.udes.backend.services;

import co.edu.udes.backend.dto.AbsenceJustificationDTO;
import co.edu.udes.backend.mappers.AbsenceJustificationMapper;
import co.edu.udes.backend.repositories.AbsenceJustificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AbsenceJustificationService {

    private final AbsenceJustificationRepository absenceJustificationRepository;
    private final AbsenceJustificationMapper absenceJustificationMapper;

    public List<AbsenceJustificationDTO> getAll() {
        return absenceJustificationRepository.findAll().stream()
                .map(absenceJustificationMapper::toDTO)
                .toList();
    }

    public AbsenceJustificationDTO getById(Long id) {
        return absenceJustificationMapper.toDTO(absenceJustificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Absence justification not found with id: " + id)));
    }

    public AbsenceJustificationDTO create(AbsenceJustificationDTO dto) {
        return absenceJustificationMapper.toDTO(absenceJustificationRepository.save(absenceJustificationMapper.toEntity(dto)));
    }

    public AbsenceJustificationDTO update(Long id, AbsenceJustificationDTO dto) {
        absenceJustificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Absence justification not found with id: " + id));
        dto.setId(id);
        return absenceJustificationMapper.toDTO(absenceJustificationRepository.save(absenceJustificationMapper.toEntity(dto)));
    }

    public void delete(Long id) {
        absenceJustificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Absence justification not found with id: " + id));
        absenceJustificationRepository.deleteById(id);
    }
}
