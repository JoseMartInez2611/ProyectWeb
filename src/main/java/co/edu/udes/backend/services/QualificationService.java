package co.edu.udes.backend.services;

import co.edu.udes.backend.dto.QualificationDTO;
import co.edu.udes.backend.mappers.QualificationMapper;
import co.edu.udes.backend.models.Qualification;
import co.edu.udes.backend.models.Student;
import co.edu.udes.backend.models.inheritance.Evaluation;
import co.edu.udes.backend.repositories.EvaluationRepository;
import co.edu.udes.backend.repositories.QualificationRepository;
import co.edu.udes.backend.repositories.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
public class QualificationService {

    private final QualificationRepository qualificationRepository;
    @Autowired
    private QualificationMapper qualificationMapper;
    private final StudentRepository studentRepository;
    private final EvaluationRepository evaluationRepository;

    public List<QualificationDTO> getAll() {
        List<Qualification> qualifications =   qualificationRepository.findAll();
        return qualificationMapper.toDtoList(qualifications);
    }

    public QualificationDTO getById(Long id) {
        return qualificationMapper.toDto(qualificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Qualification not found with id: " + id)));
    }

    public QualificationDTO create(Qualification qualification) {
        return qualificationMapper.toDto(qualificationRepository.save(qualification));
    }

    public List<QualificationDTO> createMultiple(List<Qualification> data) {
        validateQualification(data);
        System.out.println("Service "+data);
        getIds(data);
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

    public void getIds(List<Qualification> data) {
        System.out.println("Ingresa a getIds");
        for (Qualification q : data) {
            if (q.getStudent() != null && q.getStudent().getId() != 0) {
                Student student = studentRepository.findById(q.getStudent().getId())
                        .orElseThrow(() -> new RuntimeException("Student not found with ID: " + q.getStudent().getId()));
                q.setStudent(student);
            }

            if (q.getEvaluation() != null && q.getEvaluation().getId() != 0) {
                Evaluation evaluation = evaluationRepository.findById(q.getEvaluation().getId())
                        .orElseThrow(() -> new RuntimeException("Evaluation not found with ID: " + q.getEvaluation().getId()));
                q.setEvaluation(evaluation);
            }
        }
    }


    public float getObject(List<?> data, int i) {
        Qualification qualification = (Qualification)data.get(i);
        return  qualification.getQualification();
    }


    public void validateQualification(List<?> data) {

        for(int i = 0; i < data.size(); i++) {
            float x= getObject(data, i);
            if(x < 0 || x > 5 ) {
                throw new RuntimeException("Qualification must be between 0 and 5");
            }

        }

    }

    public Double getAverage(long id){

        Double average=qualificationRepository.findAverageScoreByStudentId(id);
        return average != null ? average.floatValue() : 0.0;

    }



}
