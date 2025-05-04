package co.edu.udes.backend.services;

import co.edu.udes.backend.dto.LessonDTO;
import co.edu.udes.backend.mappers.LessonMapper;
import co.edu.udes.backend.models.*;
import co.edu.udes.backend.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LessonService {

    private final LessonRepository lessonRepository;
    private final ScheduleRepository scheduleRepository;
    private final RoomRepository roomRepository;
    private final AcademicRegistrationRepository academicRegistrationRepository;
    private final AcademicPeriodRepository academicPeriodRepository;
    @Autowired
    private LessonMapper lessonMapper;

    public List<LessonDTO> getAll() {
        List<Lesson> lessons = lessonRepository.findAll();
        return lessonMapper.toDtoList(lessons);
    }

    public LessonDTO getById(Long id) {
        return lessonMapper.toDto(lessonRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lesson not found with id: " + id)));
    }

    public LessonDTO create(Lesson lesson) {
        isScheduleConflict(lesson, null);
        return lessonMapper.toDto(lessonRepository.save(lesson));
    }

    public List<LessonDTO> createMultiple(List<Lesson> lessons) {
        List<LessonDTO> newLessons = new ArrayList<>();
        for (Lesson lesson : lessons) {
            newLessons.add(create(lesson));
        }
        return newLessons;
    }

    public LessonDTO update(Long id, Lesson lesson) {
        Lesson existing = lessonRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lesson not found with id: " + id));

        isScheduleConflict(lesson, id);
        roomCapacityCheck(lesson);

        existing.setGroup(lesson.getGroup());
        existing.setClassroom(lesson.getClassroom());
        existing.setSchedule(lesson.getSchedule());

        return lessonMapper.toDto(lessonRepository.save(existing));
    }

    public void delete(Long id) {
        lessonRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lesson not found with id: " + id));
        lessonRepository.deleteById(id);
    }

    private void isScheduleConflict(Lesson lesson, Long excludeId) {
        Schedule schedule = scheduleRepository.findById(lesson.getSchedule().getId())
                .orElseThrow(() -> new RuntimeException("Schedule not found with id: " + lesson.getSchedule().getId()));

        List<Lesson> lessons = lessonRepository.findBySchedule_DayOfWeekAndClassroom(
                schedule.getDayOfWeek(),
                lesson.getClassroom()
        );

        for (Lesson existingLesson : lessons) {
            if (excludeId != null && existingLesson.getId().equals(excludeId)) {
                continue;
            }

            Schedule existingSchedule = existingLesson.getSchedule();

            boolean startsBefore = schedule.getStartHour().isBefore(existingSchedule.getEndHour());
            boolean endsAfter = schedule.getEndHour().isAfter(existingSchedule.getStartHour());

            if (startsBefore && endsAfter) {
                throw new RuntimeException(
                        "The lesson conflicts with another lesson in the same classroom at the same schedule." +
                        " Please change one of them."
                );
            }
        }
    }

    private void roomCapacityCheck(Lesson lesson) {
        Room newRoom = roomRepository.findById(lesson.getClassroom().getId())
                .orElseThrow( () -> new RuntimeException("Room not found with id: " + lesson.getClassroom().getId()));

        LocalDate referenceDate = LocalDate.now();
        int year = referenceDate.getYear();
        AcademicPeriod academicPeriod = null;

        if (referenceDate.isBefore(LocalDate.of(year, 6, 1))) {
            academicPeriod = academicPeriodRepository.findByAcademicYearAndPeriod(year, 'A');
        } else {
            academicPeriod = academicPeriodRepository.findByAcademicYearAndPeriod(year, 'B');
        }

        List<AcademicRegistration> academicRegistrations = academicRegistrationRepository.findByGroupIdAndAcademicPeriodId(lesson.getGroup().getId(), academicPeriod.getId());

        if (academicRegistrations.size() > newRoom.getCapacity()) {
            throw new RuntimeException("The room capacity is not enough for the number of students in the group.");
        }
    }
}