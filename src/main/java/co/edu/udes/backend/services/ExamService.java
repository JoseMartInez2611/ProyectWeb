package co.edu.udes.backend.services;

import co.edu.udes.backend.dto.ExamDTO;
import co.edu.udes.backend.mappers.ExamMapper;
import co.edu.udes.backend.models.Exam;
import co.edu.udes.backend.models.Group;
import co.edu.udes.backend.models.QualificationCategory;
import co.edu.udes.backend.repositories.ExamRepository;
import co.edu.udes.backend.repositories.GroupRepository;
import co.edu.udes.backend.repositories.QualificationCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExamService {

    private final ExamRepository examRepository;
    private final QualificationCategoryRepository qualificationCategoryRepository;
    @Autowired
    private ExamMapper examMapper;

    public List<ExamDTO> getAll() {
        List<Exam> exams = examRepository.findAll();
        return examMapper.toDtoList(exams);
    }

    public ExamDTO getById(Long id) {
        return examMapper.toDto(examRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Exam not found with id: " + id)));
    }

    public ExamDTO create(Exam exam) {
        return examMapper.toDto(examRepository.save(exam));

    }

    public List<ExamDTO> createMultiple(List<Exam> users) {
        return examMapper.toDtoList(
                examRepository.saveAll(users)
        );
    }

    public ExamDTO update(Long id, Exam exam) {
        examRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Exam not found with id: " + id));
        exam.setId(id);
        return examMapper.toDto(examRepository.save(exam));
    }

    public void delete(Long id) {
        examRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Exam not found with id: " + id));
        examRepository.deleteById(id);
    }

    // mdulos

    /**
     * Crea un nuevo examen y lo asocia a un grupo.
     *
     * @param exam La entidad Exam a crear. Debe contener la información del grupo asociado (a través de su ID).
     * @return El ExamDTO del examen creado.
     * @throws RuntimeException Si no se encuentra el grupo con el ID proporcionado en el examen.
     */
    public ExamDTO createExam(Exam exam) {
        // Buscar la categoría asociada por el ID
        QualificationCategory qualificationCategory = qualificationCategoryRepository.findById(exam.getQualificationCategory().getId())
                .orElseThrow(() -> new RuntimeException("Qualification category not found with id: " + exam.getQualificationCategory().getId()));

        // Establecer la relación entre el examen y el grupo
        exam.setQualificationCategory(qualificationCategory);

        // Guardar el nuevo examen
        Exam savedExam = examRepository.save(exam);
        return examMapper.toDto(savedExam);
    }

    /**
     * Crea múltiples exámenes y los asocia a sus respectivos grupos.
     *
     * @param exams Una lista de entidades Exam a crear. Cada examen debe contener la información del grupo asociado.
     * @return Una lista de ExamDTO de los exámenes creados.
     */
    public List<ExamDTO> createMultipleExams(List<Exam> exams) {
        List<ExamDTO> createdExams = new ArrayList<>();
        for (Exam exam : exams) {
            createdExams.add(createExam(exam));
        }
        return createdExams;
    }
}
