package co.edu.udes.backend.services;

import co.edu.udes.backend.dto.AcademicRegistrationDTO;
import co.edu.udes.backend.mappers.AcademicRegistrationMapper;
import co.edu.udes.backend.models.*;
import co.edu.udes.backend.repositories.AcademicRecordRepository;
import co.edu.udes.backend.repositories.AcademicRegistrationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AcademicRegistrationService {

    private final AcademicRegistrationRepository academicRegistrationRepository;
    private final AcademicRecordRepository academicRecordRepository;
    @Autowired
    private AcademicRegistrationMapper academicRegistrationMapper;

    public List<AcademicRegistrationDTO> getAll() {
        List<AcademicRegistration> academicRegistrations = academicRegistrationRepository.findAll();
        return academicRegistrationMapper.toDtoList(academicRegistrations);
    }

    public AcademicRegistrationDTO getById(Long id) {
        return academicRegistrationMapper.toDto(academicRegistrationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Academic registration not found with id: " + id)));
    }

    public AcademicRegistrationDTO create(AcademicRegistration academicRegistration) {
        validatePrerequisites(academicRegistration.getStudent(), academicRegistration.getGroup());

        boolean exists = academicRegistrationRepository.existsByStudentIdAndGroupId(
                academicRegistration.getStudent().getId(),
                academicRegistration.getGroup().getId()
        );

        if (exists) {
            throw new RuntimeException("The student is already matriculated in this group");
        }

        return academicRegistrationMapper.toDto(academicRegistrationRepository.save(academicRegistration));
    }

    public List<AcademicRegistrationDTO> createMultiple(List<AcademicRegistration> academicRegistrations) {
        return academicRegistrationMapper.toDtoList(
                academicRegistrationRepository.saveAll(academicRegistrations)
        );
    }

    public AcademicRegistrationDTO update(Long id, AcademicRegistration academicRegistration) {
        academicRegistrationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Academic registration not found with id: " + id));
        academicRegistration.setId(id);
        return academicRegistrationMapper.toDto(academicRegistrationRepository.save(academicRegistration));
    }

    public void delete(Long id) {
        academicRegistrationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Academic registration not found with id: " + id));
        academicRegistrationRepository.deleteById(id);
    }

    private void validatePrerequisites(Student student, Group group) {
        Course course = group.getCourse();
        List<Course> prerequisites = course.getPrerequisites();

        if (prerequisites == null || prerequisites.isEmpty()) {
            return;
        }

        AcademicRecord record = academicRecordRepository.findByStudentId(student.getId())
                .orElseThrow(() -> new RuntimeException("Academic record not found for student with id: " + student.getId()));

        List<Long> approvedCourseIds = record.getFinalNotes().stream()
                .filter(note -> note.getNote() >= 3.0)
                .map(note -> note.getGroup().getCourse().getId())
                .toList();

        List<String> missing = prerequisites.stream()
                .filter(prereq -> !approvedCourseIds.contains(prereq.getId()))
                .map(Course::getName)
                .toList();

        if (!missing.isEmpty()) {
            throw new RuntimeException("The student cannot enroll in '" + course.getName() +
                    "' because the following prerequisite(s) have not been approved: " + String.join(", ", missing));
        }
    }
}
