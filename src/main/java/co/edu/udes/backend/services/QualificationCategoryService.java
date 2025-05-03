package co.edu.udes.backend.services;

import co.edu.udes.backend.dto.QualificationCategoryDTO;
import co.edu.udes.backend.mappers.QualificationCategoryMapper;
import co.edu.udes.backend.models.QualificationCategory;
import co.edu.udes.backend.repositories.AcademicSubperiodRepository;
import co.edu.udes.backend.repositories.QualificationCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QualificationCategoryService {

    private final QualificationCategoryRepository qualificationCategoryRepository;
    private final AcademicSubperiodRepository academicSubperiodRepository;
    @Autowired
    private QualificationCategoryMapper qualificationCategoryMapper;

    public List<QualificationCategoryDTO> getAll() {
        List<QualificationCategory> qualificationCategories = qualificationCategoryRepository.findAll();
        return qualificationCategoryMapper.toDtoList(qualificationCategories);
    }

    public QualificationCategoryDTO getById(Long id) {
        return qualificationCategoryMapper.toDto(qualificationCategoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Qualification category not found with id: " + id)));
    }

    public QualificationCategoryDTO create(QualificationCategory qualificationCategory) {
        return qualificationCategoryMapper.toDto(qualificationCategoryRepository.save(qualificationCategory));
    }

    public List<QualificationCategoryDTO> createMultiple(List<QualificationCategory> qualificationCategories) {
        List<QualificationCategoryDTO> newQualificationCategories = new ArrayList<>();
        checkQualificationCategoriesPercentages(qualificationCategories);
        checkQualificationCategoriesSubperiods(qualificationCategories);
        for (QualificationCategory qualificationCategory : qualificationCategories) {
            newQualificationCategories.add(create(qualificationCategory));
        }
        return newQualificationCategories;
    }

    public QualificationCategoryDTO update(Long id, QualificationCategory qualificationCategory) {
        qualificationCategoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Qualification category not found with id: " + id));
        qualificationCategory.setId(id);
        return qualificationCategoryMapper.toDto(qualificationCategoryRepository.save(qualificationCategory));
    }

    public void delete(Long id) {
        qualificationCategoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Qualification category not found with id: " + id));
        qualificationCategoryRepository.deleteById(id);
    }

    private void checkQualificationCategoriesPercentages(List<QualificationCategory> qualificationCategories) {
        int totalPercentage = 0;
        for (QualificationCategory qualificationCategory : qualificationCategories) {
            totalPercentage += qualificationCategory.getPercentage();
        }
        if (totalPercentage != 100) {
            throw new RuntimeException("The sum of the percentages must be equal to 100");
        }
    }

    private void checkQualificationCategoriesSubperiods(List<QualificationCategory> qualificationCategories) {
        for (QualificationCategory qualificationCategory : qualificationCategories) {
            academicSubperiodRepository.findById(qualificationCategory.getAcademicSubperiod().getId())
                    .orElseThrow(() -> new RuntimeException("Academic subperiod not found with id: " + qualificationCategory.getAcademicSubperiod().getId()));
        }
    }
}
