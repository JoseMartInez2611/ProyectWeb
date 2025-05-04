package co.edu.udes.backend.services;

import co.edu.udes.backend.dto.AcademicSubperiodDTO;
import co.edu.udes.backend.mappers.AcademicSubperiodMapper;
import co.edu.udes.backend.models.AcademicSubperiod;
import co.edu.udes.backend.repositories.AcademicSubperiodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AcademicSubperiodService {
    private final AcademicSubperiodRepository academicSubperiodRepository;
    @Autowired
    private AcademicSubperiodMapper academicSubperiodMapper;

    public List<AcademicSubperiodDTO> getAll() {
        List<AcademicSubperiod> academicSubperiods = academicSubperiodRepository.findAll();
        return academicSubperiodMapper.toDtoList(academicSubperiods);
    }

    public AcademicSubperiodDTO getById(Long id) {
        return academicSubperiodMapper.toDto(academicSubperiodRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Academic subperiod not found with id: " + id)));
    }

    public AcademicSubperiodDTO create(AcademicSubperiod academicSubperiod) {
        return academicSubperiodMapper.toDto(academicSubperiodRepository.save(academicSubperiod));
    }

    public List<AcademicSubperiodDTO> createMultiple(List<AcademicSubperiod> academicSubperiods) {
        List<AcademicSubperiodDTO> newAcademicSubperiods = new ArrayList<>();
        checkAcademicSubperiodPercentages(academicSubperiods);
        for (AcademicSubperiod academicSubperiod : academicSubperiods) {
            newAcademicSubperiods.add(create(academicSubperiod));
        }
        return newAcademicSubperiods;
    }

    public AcademicSubperiodDTO update(Long id, AcademicSubperiod academicSubperiod) {
        academicSubperiodRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Academic subperiod not found with id: " + id));
        academicSubperiod.setId(id);
        return academicSubperiodMapper.toDto(academicSubperiodRepository.save(academicSubperiod));
    }

    public void delete(Long id) {
        academicSubperiodRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Academic subperiod not found with id: " + id));
        academicSubperiodRepository.deleteById(id);
    }

    private void checkAcademicSubperiodPercentages(List<AcademicSubperiod> academicSubperiods) {
        int totalPercentage = 0;
        for (AcademicSubperiod academicSubperiod : academicSubperiods) {
            totalPercentage += academicSubperiod.getPercentage();
        }
        if (totalPercentage != 100) {
            throw new RuntimeException("The sum of the percentages must be equal to 100");
        }
    }
}
