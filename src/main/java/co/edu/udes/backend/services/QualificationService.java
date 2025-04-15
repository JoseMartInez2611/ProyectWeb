package co.edu.udes.backend.services;

import co.edu.udes.backend.dto.QualificationDTO;
import co.edu.udes.backend.mappers.QualificationMapper;
import co.edu.udes.backend.models.Qualification;
import co.edu.udes.backend.repositories.QualificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Printable;
import java.security.Principal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QualificationService {

    private final QualificationRepository qualificationRepository;
    @Autowired
    private QualificationMapper qualificationMapper;

    public List<QualificationDTO> getAll() {
        List<Qualification> qualifications =   qualificationRepository.findAll();
        return qualificationMapper.toDtoList(qualifications);
    }

    public QualificationDTO getById(Long id) {
        return qualificationMapper.toDto(qualificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Qualification not found with id: " + id)));
    }

    public QualificationDTO create(Qualification qualification) {
        validateQualification(qualification.getQualification());
        System.out.println("salio");
        return qualificationMapper.toDto(qualificationRepository.save(qualification));
    }

    public List<QualificationDTO> createMultiple(List<Qualification> users) {
        return qualificationMapper.toDtoList(
                qualificationRepository.saveAll(users)
        );
    }

    public QualificationDTO update(Long id, Qualification qualification) {
        qualificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Qualification not found with id: " + id));
        qualification.setId(id);
        return qualificationMapper.toDto(qualificationRepository.save(qualification));

    }

    public void delete(Long id) {
        qualificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Qualification not found with id: " + id));
        qualificationRepository.deleteById(id);
    }

    public void validateQualification(float qualification) {
            System.out.println("Entro");
            if(qualification < 0 || qualification > 5) {
                throw new RuntimeException("Qualification must be between 0 and 5");
            }
    }
}
