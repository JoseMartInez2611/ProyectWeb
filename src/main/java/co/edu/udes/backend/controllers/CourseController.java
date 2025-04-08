package co.edu.udes.backend.controllers;

import co.edu.udes.backend.dto.CourseDTO;
import co.edu.udes.backend.mappers.CourseMapper;
import co.edu.udes.backend.models.Course;
import co.edu.udes.backend.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// @CrossOrigin(origins = "http://localhost")
@RestController
@RequestMapping("/api/v1/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private CourseMapper courseMapper;

    // get all courses
    @GetMapping
    public ResponseEntity<List<CourseDTO>> getAllCourses() {
        return ResponseEntity.ok(courseService.getAll());
    }

    // create course rest api
    @PostMapping
    public ResponseEntity<?> create(@RequestBody CourseDTO dto) {
        try{
            Course course = courseMapper.toEntity(dto);
            return ResponseEntity.ok(courseService.create(course));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Please check the data you are sending");
        }
    }

    // get course by id rest api
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try{
            return ResponseEntity.ok().body(courseService.getById(id));
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // update course rest api
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody CourseDTO dto) {
        try{
            Course course = courseMapper.toEntity(dto);
            return ResponseEntity.ok(courseService.update(id, course));
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Please check the data you are sending");
        }
    }

    // delete course rest api
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            courseService.delete(id);
            return ResponseEntity.noContent().build();
        }catch (RuntimeException e) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());}
    }
}
