package co.edu.udes.backend.controllers;

import co.edu.udes.backend.models.Lesson;
import co.edu.udes.backend.repositories.LessonRepository;
import co.edu.udes.backend.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// @CrossOrigin(origins = "http://localhost")
@RestController
@RequestMapping("/api/v1/")
public class LessonController {

    @Autowired
    private LessonRepository lessonRepository;

    // get all lessons
    @GetMapping("/lessons")
    public List<Lesson> getAllLessons() {
        return lessonRepository.findAll();
    }

    // create lesson rest api
    @PostMapping("/lessons")
    public Lesson createLesson(@RequestBody Lesson lesson) {
        return lessonRepository.save(lesson);
    }

    // get lesson by id rest api
    @GetMapping("/lessons/{id}")
    public ResponseEntity<Lesson> getLessonById(@PathVariable Long id) {
        Lesson lesson = lessonRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lesson not exist with id :" + id));
        return ResponseEntity.ok(lesson);
    }

    // update lesson rest api
    @PutMapping("/lessons/{id}")
    public ResponseEntity<Lesson> updateLesson(@PathVariable Long id, @RequestBody Lesson lessonDetails) {
        Lesson lesson = lessonRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lesson not exist with id :" + id));

        lesson.setSchedule(lessonDetails.getSchedule());
        lesson.setClassroom(lessonDetails.getClassroom());
        lesson.setGroup(lessonDetails.getGroup());

        Lesson updatedLesson = lessonRepository.save(lesson);
        return ResponseEntity.ok(updatedLesson);
    }

    // delete lesson rest api
    @DeleteMapping("/lessons/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteLesson(@PathVariable Long id) {
        Lesson lesson = lessonRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lesson not exist with id :" + id));

        lessonRepository.delete(lesson);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
