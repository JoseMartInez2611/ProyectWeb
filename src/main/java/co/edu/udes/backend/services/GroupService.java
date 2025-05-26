package co.edu.udes.backend.services;

import co.edu.udes.backend.dto.GroupDTO;
import co.edu.udes.backend.dto.LessonDTO;
import co.edu.udes.backend.dto.StudentDTO;
import co.edu.udes.backend.mappers.GroupMapper;
import co.edu.udes.backend.mappers.LessonMapper;
import co.edu.udes.backend.mappers.StudentMapper;
import co.edu.udes.backend.models.AcademicRegistration;
import co.edu.udes.backend.models.Group;
import co.edu.udes.backend.models.Lesson;
import co.edu.udes.backend.models.Student;
import co.edu.udes.backend.repositories.AcademicRegistrationRepository;
import co.edu.udes.backend.repositories.GroupRepository;
import co.edu.udes.backend.repositories.LessonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;
    private final AcademicRegistrationRepository academicRegistrationRepository;
    private final LessonRepository lessonRepository;

    @Autowired
    private GroupMapper groupMapper;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private LessonMapper lessonMapper;

    public List<GroupDTO> getAll() {
        List<Group> groups = groupRepository.findAll();
        return groupMapper.toDtoList(groups);
    }

    public GroupDTO getById(Long id) {
        return groupMapper.toDto(groupRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Group not found with id: " + id)));
    }

    public GroupDTO create(Group group) {
        return groupMapper.toDto(groupRepository.save(group));
    }

    public List<GroupDTO> createMultiple(List<Group> groups) {
        return groupMapper.toDtoList(
                groupRepository.saveAll(groups)
        );
    }


    public GroupDTO update(Long id, Group group) {
        groupRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Group not found with id: " + id));
        group.setId(id);
        return groupMapper.toDto(groupRepository.save(group));
    }

    public void delete(Long id) {
        groupRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Group not found with id: " + id));
        groupRepository.deleteById(id);
    }

    public List<StudentDTO> getStudentsByGroupId(Long groupId) {
        List<AcademicRegistration> registrations = academicRegistrationRepository.findByGroupId(groupId);
        List<Student> students = new ArrayList<>();

        for (AcademicRegistration registration : registrations) {
            Student student = registration.getStudent();
            students.add(student);
        }

        return studentMapper.toDtoList(students);
    }

    public List<LessonDTO> getLessonsByGroupId(Long groupId) {
        List<Lesson> lessons = lessonRepository.findByGroupId(groupId);
        return lessonMapper.toDtoList(lessons);
    }
}
