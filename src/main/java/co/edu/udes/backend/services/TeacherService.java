package co.edu.udes.backend.services;

import co.edu.udes.backend.dto.TeacherDTO;
import co.edu.udes.backend.dto.inheritanceDTO.EvaluationDTO;
import co.edu.udes.backend.mappers.EvaluationMapper;
import co.edu.udes.backend.mappers.TeacherMapper;
import co.edu.udes.backend.models.Group;
import co.edu.udes.backend.models.Lesson;
import co.edu.udes.backend.models.Teacher;
import co.edu.udes.backend.models.inheritance.Evaluation;
import co.edu.udes.backend.repositories.EvaluationRepository;
import co.edu.udes.backend.repositories.GroupRepository;
import co.edu.udes.backend.repositories.LessonRepository;
import co.edu.udes.backend.repositories.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final TeacherRepository teacherRepository; // Repositorio para acceder a los datos de los docentes
    private final GroupRepository groupRepository; // Repositorio para acceder a los datos de los grupos
    private final EvaluationRepository evaluationRepository; // Repositorio para acceder a los datos de las evaluaciones
    private final LessonRepository lessonRepository;
    @Autowired
    private TeacherMapper teacherMapper; // Mapper para convertir entre la entidad Teacher y el DTO TeacherDTO
    @Autowired
    private final EvaluationMapper evaluationMapper; // Mapper para convertir entre la entidad Evaluation y el DTO EvaluationDTO


    /**
     * Obtiene todos los docentes.
     *
     * @return Una lista de TeacherDTO.
     */
    public List<TeacherDTO> getAll() {
        List<Teacher> teachers = teacherRepository.findAll();
        return teacherMapper.toDtoList(teachers);
    }

    /**
     * Obtiene un docente por su ID.
     *
     * @param id El ID del docente a buscar.
     * @return Un TeacherDTO.
     * @throws RuntimeException Si no se encuentra el docente con el ID proporcionado.
     */
    public TeacherDTO getById(Long id) {
        return teacherMapper.toDto(teacherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher not found with id: " + id)));
    }

    /**
     * Crea un nuevo docente.
     * @param teacher La entidad Teacher a guardar.
     * @return El TeacherDTO del docente creado.
     */
    public TeacherDTO create(Teacher teacher) {
        return teacherMapper.toDto(teacherRepository.save(teacher));
    }

    /**
     * Crea múltiples docentes.
     * @param data Una lista de entidades Teacher a guardar.
     * @return Una lista de TeacherDTO de los docentes creados.
     */
    public List<TeacherDTO> createMultiple(List<Teacher> data) {
        return teacherMapper.toDtoList(
                teacherRepository.saveAll(data)
        );
    }

    /**
     * Actualiza la información de un docente existente.
     * @param id El ID del docente a actualizar.
     * @param teacher La entidad Teacher con la información actualizada.
     * @return El TeacherDTO del docente actualizado.
     * @throws RuntimeException Si no se encuentra el docente con el ID proporcionado.
     */
    public TeacherDTO update(Long id, Teacher teacher) {
        teacherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher not found with id: " + id));
        teacher.setId(id);
        return teacherMapper.toDto(teacherRepository.save(teacher));
    }

    /**
     * Elimina un docente por su ID.
     * @param id El ID del docente a eliminar.
     * @throws RuntimeException Si no se encuentra el docente con el ID proporcionado.
     */
    public void delete(Long id) {
        teacherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher not found with id: " + id));
        teacherRepository.deleteById(id);
    }


    // modulos
    // moduló de Asignación de cursos

    /**
     * Asigna un grupo a un docente, verificando la carrera y el horario.
     * @param teacherId ID del docente.
     * @param groupId ID del grupo.
     * @return TeacherDTO del docente actualizado.
     * @throws RuntimeException Si no se encuentra el docente o el grupo,
     * si la carrera del docente no es adecuada o si hay conflicto de horario.
     */
    @Transactional
    public TeacherDTO assignGroup(Long teacherId, Long groupId) {
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Docente no encontrado con el ID: " + teacherId));
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Grupo no encontrado con el ID: " + groupId));

        // Verificar que la carrera del profesor sea adecuada para el grupo
        if (group.getCourse() != null && teacher.getCareers() != null) {
            boolean careerMatch = teacher.getCareers().stream()
                    .anyMatch(career -> group.getCourse().getCareers().contains(career));
            if (!careerMatch) {
                throw new RuntimeException("La carrera del docente no es adecuada para este grupo.");
            }
        }

        // Verificar que el horario del docente no se solape con el horario del grupo
        if (isScheduleConflict(teacher, group)) {
            throw new RuntimeException("El horario del docente se solapa con el horario del grupo.");
        }

        // Asignar el grupo al docente
        group.setTeacher(teacher);
        groupRepository.save(group);

        return teacherMapper.toDto(teacher);
    }

    /**
     * Verifica si existe un conflicto de horario entre un docente y un grupo,
     * comparando las lecciones del grupo a asignar con las lecciones de los grupos ya asignados al docente.
     *
     * @param teacher El docente al que se intenta asignar el grupo.
     * @param group   El grupo que se intenta asignar al docente.
     * @return true si hay conflicto de horario, false de lo contrario.
     */
    private boolean isScheduleConflict(Teacher teacher, Group group) {
        List<Lesson> newGroupLessons = lessonRepository.findByGroupId(group.getId());

        if (teacher.getGroups() == null || teacher.getGroups().isEmpty() || newGroupLessons.isEmpty()) {
            return false; // El profesor no tiene grupos asignados o el nuevo grupo no tiene lecciones
        }

        for (Group existingGroup : teacher.getGroups()) {
            List<Lesson> existingGroupLessons = lessonRepository.findByGroupId(existingGroup.getId());
            for (Lesson existingLesson : existingGroupLessons) {
                for (Lesson newLesson : newGroupLessons) {
                    if (existingLesson.getSchedule() != null && newLesson.getSchedule() != null) {
                        if (existingLesson.getSchedule().getDayOfWeek().getId()
                                .equals(newLesson.getSchedule().getDayOfWeek().getId())) {
                            // Verificar si los intervalos de tiempo se solapan
                            if (existingLesson.getSchedule().getStartHour()
                                    .isBefore(newLesson.getSchedule().getEndHour()) &&
                                    newLesson.getSchedule().getStartHour()
                                            .isBefore(existingLesson.getSchedule().getEndHour())) {
                                return true; // Solapamiento encontrado
                            }
                        }
                    }
                }
            }
        }
        return false; // No hay solapamiento de horarios
    }

    /**
     * Desasigna un grupo de un docente.
     * @param teacherId ID del docente.
     * @param groupId ID del grupo.
     * @return TeacherDTO del docente actualizado.
     * @throws RuntimeException Si no se encuentra el docente o el grupo,
     * o si el grupo no está asignado al docente.
     */
    @Transactional
    public TeacherDTO unassignGroup(Long teacherId, Long groupId) {
        Teacher teacherToUnassignFrom = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Docente no encontrado con el ID: " + teacherId));
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Grupo no encontrado con el ID: " + groupId));

        if (group.getTeacher() != null && group.getTeacher().getId() == teacherId) {
            Teacher defaultTeacher = teacherRepository.findById(101L)
                    .orElseThrow(() -> new RuntimeException("Docente por defecto con ID: 101 no encontrado. No se puede desasignar."));
            group.setTeacher(defaultTeacher);
            groupRepository.save(group);
            return teacherMapper.toDto(teacherToUnassignFrom);
        } else {
            throw new RuntimeException("El grupo con ID: " + groupId + " no está asignado al docente con ID: " + teacherId);
        }
    }

    /**
     * Obtiene las evaluaciones de los grupos de un docente.
     * @param teacherId ID del docente.
     * @return Lista de EvaluationDTO.
     * @throws RuntimeException Si no se encuentra el docente.
     */
    public List<EvaluationDTO> getTeacherEvaluations(Long teacherId) {
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Docente no encontrado con el ID: " + teacherId));
        List<Group> groups = teacher.getGroups();
        List<Evaluation> evaluations = new ArrayList<>();
        for (Group group : groups) {
            List<Evaluation> groupEvaluations = evaluationRepository.findByGroup(group);
            evaluations.addAll(groupEvaluations);
        }
        return evaluationMapper.toDtoList(evaluations);
    }
}
