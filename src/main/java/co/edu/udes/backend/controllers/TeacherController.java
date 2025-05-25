package co.edu.udes.backend.controllers;

import co.edu.udes.backend.dto.ScheduleInfoDTO;
import co.edu.udes.backend.dto.TeacherDTO;
import co.edu.udes.backend.mappers.TeacherMapper;
import co.edu.udes.backend.models.Teacher;
import co.edu.udes.backend.services.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/teachers")
@RequiredArgsConstructor
public class TeacherController {

    @Autowired
    private final TeacherService teacherService; // Inyecta el servicio de docentes

    @Autowired
    private final TeacherMapper teacherMapper; // Inyecta el mapper de docentes

    /**
     * Endpoint para obtener todos los docentes.
     *
     * @return ResponseEntity con la lista de TeacherDTO y estado OK (200).
     */
    @GetMapping
    public ResponseEntity<List<TeacherDTO>> getAll() {
        return ResponseEntity.ok(teacherService.getAll());
    }

    /**
     * Endpoint para obtener un docente por su ID.
     * @param id El ID del docente a buscar (viene en la ruta).
     * @return ResponseEntity con el TeacherDTO y estado OK (200) si se encuentra,
     * o estado NOT_FOUND (404) con el mensaje de error si no se encuentra.
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try{
            return ResponseEntity.ok().body(teacherService.getById(id));
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    /**
     * Endpoint para crear uno o varios docentes.
     * @param dto Una lista de TeacherDTO en el cuerpo de la petición (JSON).
     * @return ResponseEntity con la lista de TeacherDTO de los docentes creados y estado OK (200),
     * o estado BAD_REQUEST (400) si los datos enviados son incorrectos.
     */
    @PostMapping
    public ResponseEntity<?> create(@RequestBody TeacherDTO dto){
        try{
            Teacher entities = teacherMapper.toEntity(dto);
            return ResponseEntity.ok(teacherService.create(entities));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Please check the data you are sending" + e.getMessage());
        }
    }

    /**
     * Endpoint para actualizar la información de un docente existente.
     * @param id El ID del docente a actualizar (viene en la ruta).
     * @param dto El TeacherDTO con la información actualizada (viene en el cuerpo de la petición JSON).
     * @return ResponseEntity con el TeacherDTO actualizado y estado OK (200) si se encuentra,
     * o estado NOT_FOUND (404) si no se encuentra,
     * o estado BAD_REQUEST (400) si los datos enviados son incorrectos.
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody TeacherDTO dto) {
        try{
            Teacher teacher = teacherMapper.toEntity(dto);
            return ResponseEntity.ok(teacherService.update(id, teacher));
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Please check the data you are sending");
        }
    }

    /**
     * Endpoint para eliminar un docente por su ID.
     * @param id El ID del docente a eliminar (viene en la ruta).
     * @return ResponseEntity con estado NO_CONTENT (204) si la eliminación fue exitosa,
     * o estado NOT_FOUND (404) si no se encuentra el docente.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try{
            teacherService.delete(id);
            return ResponseEntity.noContent().build();
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


    // modulos

    /**
     * Endpoint para asignar un grupo (curso) a un docente.
     *
     * @param teacherId El ID del docente al que se asignará el grupo (viene en la ruta).
     * @param groupId   El ID del grupo (curso) a asignar (viene en la ruta).
     * @return ResponseEntity con el TeacherDTO actualizado y estado OK (200) si la asignación fue exitosa,
     * o estado BAD_REQUEST (400) si ocurre un error durante la asignación (ej: carrera no adecuada, solapamiento de horario),
     * o estado NOT_FOUND (404) si no se encuentra el docente o el grupo.
     */
    @PostMapping("/{teacherId}/groups/{groupId}")
    public ResponseEntity<?> assignGroup(@PathVariable Long teacherId, @PathVariable Long groupId) {
        try {
            return ResponseEntity.ok(teacherService.assignGroup(teacherId, groupId));
        } catch (RuntimeException e) {
            // Capturamos la excepción lanzada por el servicio (carrera no adecuada o solapamiento de horario)
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Endpoint para desasignar un grupo (curso) de un docente.
     *
     * @param teacherId El ID del docente del que se desasignará el grupo (viene en la ruta).
     * @param groupId   El ID del grupo (curso) a desasignar (viene en la ruta).
     * @return ResponseEntity con el TeacherDTO actualizado y estado OK (200) si la desasignación fue exitosa,
     * o estado BAD_REQUEST (400) si el grupo no está asignado al docente,
     * o estado NOT_FOUND (404) si no se encuentra el docente o el grupo.
     */
    @DeleteMapping("/{teacherId}/groups/{groupId}")
    public ResponseEntity<?> unassignGroup(@PathVariable Long teacherId, @PathVariable Long groupId) {
        try {
            return ResponseEntity.ok(teacherService.unassignGroup(teacherId, groupId));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Endpoint para obtener todas las evaluaciones asociadas a los grupos de un docente.
     *
     * @param teacherId El ID del docente del que se quieren obtener las evaluaciones (viene en la ruta).
     * @return ResponseEntity con la lista de EvaluationDTO y estado OK (200) si se encuentran evaluaciones,
     * o estado NOT_FOUND (404) si no se encuentra el docente.
     */
    @GetMapping("/{teacherId}/evaluations")
    public ResponseEntity<?> getTeacherEvaluations(@PathVariable Long teacherId) {
        try {
            return ResponseEntity.ok(teacherService.getTeacherEvaluations(teacherId));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/{teacherId}/schedule")
    public ResponseEntity<?> getTeacherLessonsFormattedInfo(@PathVariable Long teacherId) {
        List<ScheduleInfoDTO> lessonsInfo = teacherService.getTeacherSchedule(teacherId);
        return ResponseEntity.ok(lessonsInfo);
    }

}
