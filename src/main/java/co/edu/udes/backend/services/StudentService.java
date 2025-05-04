package co.edu.udes.backend.services;

import co.edu.udes.backend.dto.ScheduleInfoDTO;
import co.edu.udes.backend.dto.StudentDTO;
import co.edu.udes.backend.mappers.StudentMapper;
import co.edu.udes.backend.models.*;
import co.edu.udes.backend.repositories.*;
import co.edu.udes.backend.utils.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final AcademicRecordRepository academicRecordRepository;
    private final AcademicRegistrationRepository academicRegistrationRepository;
    private final LessonRepository lessonRepository;

    private final AcademicPeriodRepository academicPeriodRepository;
    private final RoleRepository roleRepository;

    @Autowired
    private StudentMapper studentMapper;
    private static final List<String> DAY_ORDER = List.of(
            "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY"
    );

    public List<StudentDTO> getAll() {
        List<Student> students = studentRepository.findAll();
        return studentMapper.toDtoList(students);
    }

    public StudentDTO getById(Long id) {
        return studentMapper.toDto(studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id)));
    }

    public StudentDTO create(Student student) {
        student.setRole(roleRepository.findById(3L)
                .orElseThrow(() -> new RuntimeException("Role not found")));
        student.setEnable(true);
        student.setAccountNonExpired(true);
        student.setAccountNonLocked(true);
        student.setCredentialsNonExpired(true);

        Student newStudent = studentRepository.save(student);

        AcademicRecord academicRecord = new AcademicRecord();
        academicRecord.setStudent(newStudent);
        academicRecord.setAcademicAverage(0);
        academicRecordRepository.save(academicRecord);

        return studentMapper.toDto(newStudent);
    }

    public List<StudentDTO> createMultiple(List<Student> users) {
        users = encriptPassword(users);
        List<StudentDTO> savedStudents = new ArrayList<>();
        for (Student student : users) {
            savedStudents.add(create(student));
        }
        return savedStudents;
    }

    public StudentDTO update(Long id, Student student) {
        studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
        student.setId(id);
        return studentMapper.toDto(studentRepository.save(student));

    }

    public void delete(Long id) {
        studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
        studentRepository.deleteById(id);
    }

    public List<Student> encriptPassword(List<Student> students) {
        for (Student student : students) {
            student.setPassword(new BCryptPasswordEncoder().encode(student.getPassword()));
        }
        return students;
    }

    public List<ScheduleInfoDTO> getSchedule(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));

        LocalDate referenceDate = LocalDate.now();
        int year = referenceDate.getYear();
        AcademicPeriod academicPeriod = null;

        if (referenceDate.isBefore(LocalDate.of(year, 6, 1))) {
            academicPeriod = academicPeriodRepository.findByAcademicYearAndPeriod(year, 'A');
        } else {
            academicPeriod = academicPeriodRepository.findByAcademicYearAndPeriod(year, 'B');
        }

        List<AcademicRegistration> academicRegistrations = academicRegistrationRepository.findByGroupIdAndAcademicPeriodId(id, academicPeriod.getId());
        List<ScheduleInfoDTO> scheduleInfoDTOList = new ArrayList<>();

        for (AcademicRegistration academicRegistration : academicRegistrations) {
            Group group = academicRegistration.getGroup();
            Course course = group.getCourse();
            List<Lesson> lessons = lessonRepository.findByGroupId(group.getId());

            for (Lesson lesson : lessons) {
                Schedule schedule = lesson.getSchedule();
                Room room = lesson.getClassroom();

                ScheduleInfoDTO scheduleInfoDTO = new ScheduleInfoDTO();
                scheduleInfoDTO.setCourseName(course.getName());
                scheduleInfoDTO.setRoomCode(room.getBuilding() + "-" + room.getFloor() + room.getNumber());
                scheduleInfoDTO.setDay(schedule.getDayOfWeek().getDay());
                scheduleInfoDTO.setStartTime(schedule.getStartHour());
                scheduleInfoDTO.setEndTime(schedule.getEndHour());

                scheduleInfoDTOList.add(scheduleInfoDTO);
            }
        }

        scheduleInfoDTOList.sort(Comparator
                .comparing((ScheduleInfoDTO dto) -> DAY_ORDER.indexOf(dto.getDay()))
                .thenComparing(ScheduleInfoDTO::getStartTime)
        );

        return scheduleInfoDTOList;
    }
}
