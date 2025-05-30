package co.edu.udes.backend.services;

import co.edu.udes.backend.dto.AcademicPeriodDTO;
import co.edu.udes.backend.mappers.AcademicPeriodMapper;
import co.edu.udes.backend.models.AcademicPeriod;
import co.edu.udes.backend.repositories.AcademicPeriodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AcademicPeriodService {
    private final AcademicPeriodRepository academicPeriodRepository;
    @Autowired
    private AcademicPeriodMapper academicPeriodMapper;

    public List<AcademicPeriodDTO> getAll() {
        List<AcademicPeriod> academicPeriods = academicPeriodRepository.findAll();
        return academicPeriodMapper.toDtoList(academicPeriods);
    }

    public AcademicPeriodDTO getById(Long id) {
        return academicPeriodMapper.toDto(academicPeriodRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Academic period not found with id: " + id)));
    }

    public AcademicPeriodDTO create(AcademicPeriod academicPeriod) {
        return academicPeriodMapper.toDto(academicPeriodRepository.save(academicPeriod));
    }

    public AcademicPeriodDTO getCurrentAcademicPeriod(){
        LocalDate referenceDate = LocalDate.now();
        int year = referenceDate.getYear();
        AcademicPeriod academicPeriod = null;

        if (referenceDate.isBefore(LocalDate.of(year, 6, 1))) {
            academicPeriod = academicPeriodRepository.findByAcademicYearAndPeriod(year, 'A');
        } else {
            academicPeriod = academicPeriodRepository.findByAcademicYearAndPeriod(year, 'B');
        }

        if (academicPeriod == null) {
            throw new RuntimeException("No current academic period found for the reference date: " + referenceDate);
        }

        return academicPeriodMapper.toDto(academicPeriod);
    }
}
