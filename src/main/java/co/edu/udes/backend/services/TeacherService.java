package co.edu.udes.backend.services;

import co.edu.udes.backend.dto.TeacherDTO;
import co.edu.udes.backend.dto.inheritanceDTO.EvaluationDTO;
import co.edu.udes.backend.mappers.EvaluationMapper;
import co.edu.udes.backend.mappers.TeacherMapper;
import co.edu.udes.backend.models.Group;
import co.edu.udes.backend.models.Teacher;
import co.edu.udes.backend.models.inheritance.Evaluation;
import co.edu.udes.backend.repositories.EvaluationRepository;
import co.edu.udes.backend.repositories.GroupRepository;
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
     * Asigna un grupo (curso) a un docente.
     *
     * @param teacherId El ID del docente al que se asignará el grupo.
     * @param groupId   El ID del grupo (curso) a asignar.
     * @return El TeacherDTO del docente actualizado con la asignación.
     * @throws RuntimeException Si no se encuentra el docente o el grupo con los IDs proporcionados.
     */
    @Transactional // Asegura que la operación sea atómica
    public TeacherDTO assignGroup(Long teacherId, Long groupId) {
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Teacher not found with id: " + teacherId));
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Group not found with id: " + groupId));

        // Asignar el grupo al docente
        group.setTeacher(teacher);
        groupRepository.save(group);

        // Recargar el docente para reflejar los cambios en la lista de grupos (si la necesitas actualizada inmediatamente)
        Teacher updatedTeacher = teacherRepository.findById(teacherId).orElseThrow(() -> new RuntimeException("Error reloading teacher"));
        return teacherMapper.toDto(updatedTeacher);
    }

    /**
     * Desasigna un grupo (curso) de un docente.
     *
     * @param teacherId El ID del docente del que se desasignará el grupo.
     * @param groupId   El ID del grupo (curso) a desasignar.
     * @return El TeacherDTO del docente actualizado con la desasignación.
     * @throws RuntimeException Si no se encuentra el docente o el grupo con los IDs proporcionados,
     *                          o si el grupo no está asignado al docente.
     */
    @Transactional
    public TeacherDTO unassignGroup(Long teacherId, Long groupId) {
        Teacher teacherToUnassignFrom = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Teacher not found with id: " + teacherId));
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Group not found with id: " + groupId));

        if (group.getTeacher() != null && group.getTeacher().getId() == teacherId) {
            // Buscar el teacher con ID 101 para la desasignación
            Teacher defaultTeacher = teacherRepository.findById(101L)
                    .orElseThrow(() -> new RuntimeException("Default teacher with id: 101 not found. Cannot unassign."));

            // Asignar el grupo al teacher por defecto
            group.setTeacher(defaultTeacher);
            groupRepository.save(group);

            // Recargar el docente original para reflejar los cambios (si es necesario inmediatamente)
            Teacher updatedTeacher = teacherRepository.findById(teacherId).orElseThrow(() -> new RuntimeException("Error reloading teacher"));
            return teacherMapper.toDto(updatedTeacher);
        } else {
            throw new RuntimeException("Group with id: " + groupId + " is not assigned to teacher with id: " + teacherId);
        }
    }

    /**
     * Obtiene todas las evaluaciones asociadas a los grupos de un docente.
     *
     * @param teacherId El ID del docente del que se quieren obtener las evaluaciones.
     * @return Una lista de EvaluationDTO con las evaluaciones de los grupos del docente.
     * @throws RuntimeException Si no se encuentra el docente con el ID proporcionado.
     */
    public List<EvaluationDTO> getTeacherEvaluations(Long teacherId) {
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Teacher not found with id: " + teacherId));

        List<Group> groups = teacher.getGroups(); // Obtiene los grupos asociados al docente
        List<Evaluation> evaluations = new ArrayList<>();

        for (Group group : groups) {
            // Busca las evaluaciones asociadas a cada grupo
            List<Evaluation> groupEvaluations = evaluationRepository.findByGroup(group);
            evaluations.addAll(groupEvaluations);
        }

        return evaluationMapper.toDtoList(evaluations);
    }

}
