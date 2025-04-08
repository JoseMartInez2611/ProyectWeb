package co.edu.udes.backend.services;

import co.edu.udes.backend.dto.QualificationDTO;
import co.edu.udes.backend.mappers.QualificationMapper;
import co.edu.udes.backend.models.Qualification;
import co.edu.udes.backend.repositories.QualificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QualificationService {

    private final QualificationRepository qualificationRepository;

    public List<QualificationDTO> getAll() {
        List<Qualification> qualifications =   qualificationRepository.findAll();
        return QualificationMapper.INSTANCE.toDtoList(qualifications);
    }

    public QualificationDTO getById(Long id) {
        return QualificationMapper.INSTANCE.toDto(qualificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Qualification not found with id: " + id)));
    }

    public QualificationDTO create(Qualification qualification) {
        return QualificationMapper.INSTANCE.toDto(qualificationRepository.save(qualification));

    }

    public QualificationDTO update(Long id, Qualification qualification) {
        qualificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Qualification not found with id: " + id));
        qualification.setId(id);
        return QualificationMapper.INSTANCE.toDto(qualificationRepository.save(qualification));

    }

    public void delete(Long id) {
        qualificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Qualification not found with id: " + id));
        qualificationRepository.deleteById(id);
    }
}
