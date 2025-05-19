package co.edu.udes.backend.services;

import co.edu.udes.backend.dto.QualificationDTO;
import co.edu.udes.backend.mappers.QualificationMapper;
import co.edu.udes.backend.models.AcademicSubperiod;
import co.edu.udes.backend.models.FinalNote;
import co.edu.udes.backend.models.Qualification;
import co.edu.udes.backend.models.QualificationCategory;
import co.edu.udes.backend.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QualificationService {

    private final QualificationRepository qualificationRepository;
    @Autowired
    private QualificationMapper qualificationMapper;
    private final StudentRepository studentRepository;
    private final EvaluationRepository evaluationRepository;
    private final QualificationCategoryRepository  qualificationCategoryRepository;
    private final FinalNoteRepository  finalNoteRepository;
    private final AcademicSubperiodRepository academicSubperiodRepository;

    public List<QualificationDTO> getAll() {
        List<Qualification> qualifications =   qualificationRepository.findAll();
        return qualificationMapper.toDtoList(qualifications);
    }

    public QualificationDTO getById(Long id) {
        return qualificationMapper.toDto(qualificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Qualification not found with id: " + id)));
    }

    public QualificationDTO create(Qualification qualification) {
        Qualification qualificationSave = qualificationRepository.save(qualification);
        setFinalNote(qualification.getStudent().getId(), qualification.getEvaluation().getQualificationCategory().getGroup().getId());
        return qualificationMapper.toDto(qualificationSave);
    }

    public List<QualificationDTO> createMultiple(List<Qualification> data) {
        validateQualification(data);
        return qualificationMapper.toDtoList(
                qualificationRepository.saveAll(data)
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

    public float getValue(List<?> data, int i) {
        Qualification qualification = (Qualification)data.get(i);
        return  qualification.getQualification();
    }

    public void validateQualification(List<?> data) {

        for(int i = 0; i < data.size(); i++) {
            float x= getValue(data, i);
            if(x < 0 || x > 5 ) {
                throw new RuntimeException("Qualification must be between 0 and 5");
            }

        }

    }

    public void setFinalNote(Long idStudent, Long idGroup) {
        float zero=0;
        FinalNote finalNotes = finalNoteRepository.finByStundentAndGroup(idStudent, idGroup);
        List<QualificationCategory> categories = qualificationCategoryRepository.findByGroupId(idGroup);
        List<AcademicSubperiod> academicSubperiods = new ArrayList<>();

        for (QualificationCategory category : categories) {
            if (!academicSubperiods.contains(category.getAcademicSubperiod())) {
                academicSubperiods.add(category.getAcademicSubperiod());
            }
        }

        for (AcademicSubperiod academicSubperiod : academicSubperiods) {
            float cutAverage = getCutAverage(idStudent, idGroup, academicSubperiod.getId());
            zero += cutAverage*(academicSubperiod.getPercentage()/100);
        }

        finalNotes.setNote(zero);
        finalNoteRepository.save(finalNotes);
    }

    public float getCutAverage(Long idStudent, Long idGroup, Long idAcademicSubperiod) {
        float average=0;
        float sum=0;

        List<Qualification> qualifications =   qualificationRepository.findAllByStudentAndGroupIdAndCut(idStudent, idGroup, idAcademicSubperiod);
        List<QualificationCategory> categories = qualificationCategoryRepository.getAllByAcademicSubperiodIdAndGroupId(idAcademicSubperiod, idGroup);


        for (int i = 0; i < categories.size(); i++) {
            int cont = 0;
            for (int j = 0; j < qualifications.size(); j++) {
                if(qualifications.get(i).getEvaluation().getQualificationCategory().getId() == categories.get(i).getId()) {
                    sum += qualifications.get(i).getQualification();
                    cont++;
                }
            }
            sum=sum/cont;
            average+= sum*(categories.get(i).getPercentage()/100);
            sum=0;
        }

        return average;
    }


    public float getStudentAverageOfLesson(Long idStudent, Long idGroup) {
        float average = 0;
        List<Qualification> qualifications =   qualificationRepository.findByStudentIdAndEvaluationQualificationCategoryGroupId(idStudent, idGroup);

        for(int i = 0; i < qualifications.size(); i++) {
            average+= qualifications.get(i).getQualification();
        }
        average=average/qualifications.size();

        return average;
    }


    public float getAverage(Long idStudent){
        List<Qualification> qualifications =   qualificationRepository.findAllByStudentId(idStudent);

        float average=0;

        for(int i = 0; i < qualifications.size(); i++) {
            average+= qualifications.get(i).getQualification();
        }
        average=average/qualifications.size();

        return average;
    }


    public float getGroupAverage(Long idGroup){

        List<FinalNote> qualifications =  finalNoteRepository.findAllByGroupId(idGroup);

        float average=0;

        for(int i = 0; i < qualifications.size(); i++) {
            average+= qualifications.get(i).getNote();
        }
        average=average/qualifications.size();


        return average;
    }

    public float getProyectNote2(Long idAcademicRecord, Long idStudent, Long idGroup) {
        float average = 0;


        return average;
    }


    public double getProyectNote(Long idStudent, Long idGroup, Long idAcademicSubperiod) {
        double minimum = 3.0;

        // Promedio actual acumulado del corte
        double value = getCutAverage(idStudent, idGroup, idAcademicSubperiod);

        // Porcentaje de evaluaciones ya realizadas en el corte
        double usedPercentage = qualificationRepository.getPercentagesUsed(
                idStudent, idGroup, idAcademicSubperiod
        );

        double remainingPercentage = 100.0 - usedPercentage;
        double necessaryNote = (minimum - value) * 100 / remainingPercentage;

        return necessaryNote;
    }


}
