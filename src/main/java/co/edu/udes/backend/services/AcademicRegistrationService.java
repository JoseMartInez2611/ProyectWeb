package co.edu.udes.backend.services;

import co.edu.udes.backend.dto.AcademicRegistrationDTO;
import co.edu.udes.backend.mappers.AcademicRegistrationMapper;
import co.edu.udes.backend.models.*;
import co.edu.udes.backend.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AcademicRegistrationService {

    private final AcademicRegistrationRepository academicRegistrationRepository;
    private final AcademicRecordRepository academicRecordRepository;
    private final LessonRepository lessonRepository;
    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;
    private final CourseRepository courseRepository;
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
        alreadyRegistered(academicRegistration);
        validateSameCareer(academicRegistration.getStudent(), academicRegistration.getGroup());
        validatePrerequisites(academicRegistration.getStudent(), academicRegistration.getGroup());
        validateScheduleAvailability(academicRegistration.getStudent(), academicRegistration.getGroup());
        return academicRegistrationMapper.toDto(academicRegistrationRepository.save(academicRegistration));
    }

    public List<AcademicRegistrationDTO> createMultiple(List<AcademicRegistration> academicRegistrations) {

        List<AcademicRegistrationDTO> academicRegistrationDTOS = new ArrayList<>();

        for (AcademicRegistration academicRegistration : academicRegistrations) {
            AcademicRegistrationDTO newAcademicRegistration = create(academicRegistration);
            academicRegistrationDTOS.add(newAcademicRegistration);
        }
        return academicRegistrationDTOS;
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

    private void validateScheduleAvailability(Student student, Group group){
        List<Lesson> lessons = lessonRepository.findByGroupId(group.getId());
        System.out.println("Lessons: " + lessons);

        List<AcademicRegistration> currentRegistrations = academicRegistrationRepository.findByStudentId(student.getId());
        System.out.println("Current Registrations: " + currentRegistrations);

        List<Long> currentGroupIds = currentRegistrations.stream()
                .map(registration -> registration.getGroup().getId())
                .toList();
        List<Lesson> currentLessons = lessonRepository.findByGroupIdIn(currentGroupIds);
        System.out.println("Current Lessons: " + currentLessons);

        for (Lesson lesson : lessons) {
            Schedule schedule = lesson.getSchedule();

            for (Lesson currentLesson : currentLessons) {
                Schedule currentSchedule = currentLesson.getSchedule();

                if (schedulesOverlap(schedule, currentSchedule)) {
                    throw new RuntimeException("The student cannot enroll in this group because the schedule conflicts with another group.");
                }
            }
        }
    }

    private boolean schedulesOverlap(Schedule s1, Schedule s2) {
        if (!s1.getDayOfWeek().equals(s2.getDayOfWeek())) return false;
        return s1.getStartHour().isBefore(s2.getEndHour()) &&
                s1.getEndHour().isAfter(s2.getStartHour());
    }

    private void validatePrerequisites(Student student, Group group) {

        Group groupComplete = groupRepository.findById(group.getId())
                .orElseThrow(() -> new RuntimeException("Group not found with id: " + group.getId()));

        Course course = groupComplete.getCourse();
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
            throw new RuntimeException("The student cannot enroll in " + course.getName() +
                    " because the following prerequisite(s) have not been approved: " + String.join(", ", missing));
        }
    }

    private void alreadyRegistered(AcademicRegistration academicRegistration) {
        boolean exists = academicRegistrationRepository.existsByStudentIdAndGroupId(
                academicRegistration.getStudent().getId(),
                academicRegistration.getGroup().getId()
        );

        if (exists) {
            throw new RuntimeException("The student is already matriculated in this group");
        }
    }

    private void validateSameCareer(Student student, Group group){
        Student studentComplete = studentRepository.findById(student.getId())
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + student.getId()));

        Group groupComplete = groupRepository.findById(group.getId())
                .orElseThrow(() -> new RuntimeException("Group not found with id: " + group.getId()));

        Course courseWithCareers  = courseRepository.findByIdWithCareers(groupComplete.getCourse().getId())
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + groupComplete.getCourse().getId()));

        boolean belongsToSameCareer = courseWithCareers.getCareers().stream()
                .anyMatch(career -> career.getId().equals(studentComplete.getCareer().getId()));

        if (!belongsToSameCareer) {
            throw new RuntimeException("The student does not belong to the same career as the group");
        }
    }
}
