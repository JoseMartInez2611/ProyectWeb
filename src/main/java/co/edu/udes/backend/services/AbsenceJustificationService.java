package co.edu.udes.backend.services;

import co.edu.udes.backend.dto.AbsenceJustificationDTO;
import co.edu.udes.backend.mappers.AbsenceJustificationMapper;
import co.edu.udes.backend.models.AbsenceJustification;
import co.edu.udes.backend.repositories.AbsenceJustificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AbsenceJustificationService {

    private final AbsenceJustificationRepository absenceJustificationRepository;
    private AbsenceJustificationMapper absenceJustificationMapper;

    public List<AbsenceJustificationDTO> getAll() {
        List<AbsenceJustification> absenceJustifications = absenceJustificationRepository.findAll();
        return absenceJustificationMapper.toDtoList(absenceJustifications);
    }

    public AbsenceJustificationDTO getById(Long id) {
        return absenceJustificationMapper.toDto(absenceJustificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Absence justification not found with id: " + id)));
    }

    public AbsenceJustificationDTO create(AbsenceJustification absenceJustification) {
        return absenceJustificationMapper.toDto(absenceJustification);
    }

    public AbsenceJustificationDTO update(Long id, AbsenceJustification absenceJustification) {
        absenceJustificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Absence justification not found with id: " + id));
        absenceJustification.setId(id);
        return absenceJustificationMapper.toDto(
                absenceJustificationRepository.save(absenceJustification)
        );
    }

    public void delete(Long id) {
        absenceJustificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Absence justification not found with id: " + id));
        absenceJustificationRepository.deleteById(id);
    }
}
