package co.edu.udes.backend.services;

import co.edu.udes.backend.dto.AcademicRegistrationDTO;
import co.edu.udes.backend.mappers.AcademicRegistrationMapper;
import co.edu.udes.backend.models.*;
import co.edu.udes.backend.repositories.*;
import jakarta.transaction.Transactional;
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
    private final FinalNoteRepository finalNoteRepository;
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
        validateGroupCapacity(academicRegistration.getGroup());

        AcademicRegistration savedAcademicRegistration = academicRegistrationRepository.save(academicRegistration);

        AcademicRecord academicRecord = academicRecordRepository.findByStudentId(academicRegistration.getStudent().getId())
                .orElseThrow(() -> new RuntimeException("Academic record not found for student with id: " + academicRegistration.getStudent().getId()));

        return academicRegistrationMapper.toDto(savedAcademicRegistration);
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
        AcademicRegistration academicRegistration = academicRegistrationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Academic registration not found with id: " + id));

        academicRegistrationRepository.deleteById(id);
    }

    @Transactional
    public void deleteByStudentIdAndGroupId(Long studentId, Long groupId) {
        academicRegistrationRepository.findByStudentIdAndGroupId(studentId, groupId)
                .orElseThrow(() -> new RuntimeException("Academic registration not found for student with id: " + studentId + " and group with id: " + groupId));

        academicRegistrationRepository.deleteByStudentIdAndGroupId(studentId, groupId);
    }

    private void validateScheduleAvailability(Student student, Group group){
        List<Lesson> lessons = lessonRepository.findByGroupId(group.getId());

        List<AcademicRegistration> currentRegistrations = academicRegistrationRepository.findByStudentId(student.getId());

        List<Long> currentGroupIds = currentRegistrations.stream()
                .map(registration -> registration.getGroup().getId())
                .toList();
        List<Lesson> currentLessons = lessonRepository.findByGroupIdIn(currentGroupIds);

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

        Course courseWithCareer  = courseRepository.findById(groupComplete.getCourse().getId())
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + groupComplete.getCourse().getId()));

        boolean belongsToSameCareer = courseWithCareer.getCareer().equals(studentComplete.getCareer());

        if (!belongsToSameCareer) {
            throw new RuntimeException("The student does not belong to the same career as the group");
        }
    }

    private void validateGroupCapacity(Group group) {
        List<Lesson> lessons = lessonRepository.findByGroupId(group.getId());

        int minCapacity = lessons.stream()
                .map(Lesson::getClassroom)
                .mapToInt(Room::getCapacity)
                .min()
                .orElseThrow(
                        () -> new RuntimeException("No classrooms found for lessons in group " + group.getId())
                );

        long currentRegistrations = academicRegistrationRepository.countByGroupId(group.getId());

        if (currentRegistrations >= minCapacity) {
            throw new RuntimeException("Group is full. Minimum classroom capacity reached: " + minCapacity);
        }
    }
}
