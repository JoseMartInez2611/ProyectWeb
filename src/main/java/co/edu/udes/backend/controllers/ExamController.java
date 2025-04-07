package co.edu.udes.backend.controllers;

import co.edu.udes.backend.models.Exam;
import co.edu.udes.backend.repositories.ExamRepository;
import co.edu.udes.backend.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
public class ExamController {

    @Autowired
    private ExamRepository examRepository;

    //get all exams
    @GetMapping
    public List<Exam> getAllExams(@RequestBody Exam exam){
        return examRepository.findAll();
    }

    //Create Exam
    @PostMapping("/exams")
    public Exam createExam(@RequestBody Exam exam){
        return examRepository.save(exam);
    }

    //get exam by id
    @GetMapping("/exams/{id}")
    public ResponseEntity<Exam> getExamById(@PathVariable Long id){
        Exam exam = examRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("exam not exist with id: "+id));
        return ResponseEntity.ok(exam);

    }

    //update exam
    @PutMapping("/exams/{id}")
    public ResponseEntity<Exam> updateExam(@PathVariable Long id, @RequestBody Exam exam){
        Exam existingExam = examRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("exam not exist with id: "+id));

        existingExam.setEvaluationRubric(exam.getEvaluationRubric());
        existingExam.setDate(exam.getDate());
        existingExam.setGroup(exam.getGroup());

        existingExam.setQuestions(exam.getQuestions());

        Exam updatedExam = examRepository.save(existingExam);
        return ResponseEntity.ok(updatedExam);
    }

    //delete exam
    @DeleteMapping("/exams/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteExam(@PathVariable Long id){
        Exam exam = examRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("exam not exist with id: "+id));
        examRepository.delete(exam);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}

