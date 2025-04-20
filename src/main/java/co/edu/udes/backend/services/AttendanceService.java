package co.edu.udes.backend.services;

import co.edu.udes.backend.dto.AttendanceDTO;
import co.edu.udes.backend.mappers.AttendanceMapper;
import co.edu.udes.backend.models.Attendance;
import co.edu.udes.backend.models.Lesson;
import co.edu.udes.backend.models.Student;
import co.edu.udes.backend.repositories.AttendanceRepository;
import co.edu.udes.backend.repositories.LessonRepository;
import co.edu.udes.backend.repositories.StudentRepository;
import co.edu.udes.backend.utils.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final StudentRepository studentRepository;
    private final LessonRepository lessonRepository;
    @Autowired
    private AttendanceMapper attendanceMapper;

    public List<AttendanceDTO> getAll() {
        List<Attendance> attendances = attendanceRepository.findAll();
        return attendanceMapper.toDtoList(attendances);
    }

    public AttendanceDTO getById(Long id) {
        return attendanceMapper.toDto(attendanceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Attendance not found with id: " + id)));
    }

    public AttendanceDTO create(Attendance attendance) {
        verifyAttendanceExists(attendance);
        return attendanceMapper.toDto(attendanceRepository.save(attendance));
    }

    public List<AttendanceDTO> createMultiple(List<Attendance> attendances) {
        List<AttendanceDTO> savedAttendances = new ArrayList<>();
        for (Attendance attendance : attendances) {
            savedAttendances.add(create(attendance));
        }
        return savedAttendances;
    }

    public AttendanceDTO update(Long id, Attendance attendance) {
        Attendance existing = attendanceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Attendance not found with id: " + id));

        verifyAttendanceUpdate(existing, attendance);
        System.out.println("Attendance to update: " + attendance);
        attendance.setId(id);
        return attendanceMapper.toDto(attendanceRepository.save(attendance));
    }

    public void delete(Long id) {
        attendanceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Attendance not found with id: " + id));
        attendanceRepository.deleteById(id);
    }

    private void verifyAttendanceExists(Attendance attendance) {
        Long lessonId = attendance.getLesson().getId();
        Long studentId = attendance.getStudent().getId();
        LocalDate date = attendance.getDate();

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + studentId));

        Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new ResourceNotFoundException("Lesson not found with id: " + lessonId));

        String studentName = student.getFullName();
        String courseName = lesson.getGroup().getCourse().getName();

        boolean exists = attendanceRepository.existsByLessonIdAndStudentIdAndDate(lessonId, studentId,date);
        if (exists) {
            throw new RuntimeException("Attendance already exists for student " + studentName + " in the lesson of " + courseName + " on " + date);
        }
    }

    private void verifyAttendanceUpdate(Attendance existing, Attendance attendance){
        if (!existing.getLesson().getId().equals(attendance.getLesson().getId()) ||
                existing.getStudent().getId() != attendance.getStudent().getId() ||
                !existing.getDate().equals(attendance.getDate())) {
            throw new RuntimeException("Cannot change lesson, student or date on an existing attendance record.");
        }
    }
}
