package co.edu.udes.backend.controllers;

import co.edu.udes.backend.models.Teacher;
import co.edu.udes.backend.repositories.TeacherRepository;
import co.edu.udes.backend.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
public class TeacherController {

    @Autowired
    private TeacherRepository teacherRepository;

    // get all users
    @GetMapping
    public List<Teacher> getAllTeacher(@RequestBody Teacher teacher){
        return teacherRepository.findAll();

    }

    //Create teacher
    @PostMapping("/teachers")
    public Teacher createTeacher(@RequestBody Teacher teacher){
        return teacherRepository.save(teacher);
    }

    //Update teacher
    @PutMapping("/teachers/{id}")
    public ResponseEntity<Teacher> updateTeacher(@PathVariable Long id, @RequestBody Teacher teacher){
        Teacher existingTeacher = teacherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not exist with id :" + id));
        existingTeacher.setSpeciality(teacher.getSpeciality());
        Teacher updatedTeacher = teacherRepository.save(existingTeacher);
        return ResponseEntity.ok(updatedTeacher);
    }

    //Delete teacher
    @DeleteMapping("/teachers/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteTeacher(@PathVariable Long id){
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Teacher not exist with id: "+id));
        teacherRepository.delete(teacher);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
