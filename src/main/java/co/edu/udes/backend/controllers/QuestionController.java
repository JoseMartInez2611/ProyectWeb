package co.edu.udes.backend.controllers;

import co.edu.udes.backend.models.Question;
import co.edu.udes.backend.repositories.QuestionRepository;
import co.edu.udes.backend.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    @Autowired
    private QuestionRepository questionRepository;

    // get all questions
    @GetMapping
    public List<Question> getAllQuestions(@RequestBody Question question){
        return questionRepository.findAll();
    }

    //Create question
    @PostMapping("/questions")
    public Question createQuestion(@RequestBody Question question){
        return questionRepository.save(question);
    }

    @GetMapping("/questions/{id}")
    public ResponseEntity<Question> getQuestionById(@PathVariable Long id){
        Question question = questionRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("question not exist with id: "+id));
        return ResponseEntity.ok(question);
    }

    //Update question by id
    @PutMapping("/questions/{id}")
    public ResponseEntity<Question> updateQuestion(@PathVariable Long id, @RequestBody Question question){
        Question existingQuestion = questionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Question not exist with id :" + id));

        existingQuestion.setQuestion(question.getQuestion());
        existingQuestion.setQuestionType(question.getQuestionType());
        existingQuestion.setAnswer(question.getAnswer());

        Question updatedQuestion = questionRepository.save(existingQuestion);
        return ResponseEntity.ok(updatedQuestion);
    }

    //Delete question
    @DeleteMapping("/questions/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteQuestion(@PathVariable Long id){
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Question not exist with id :" + id));
        questionRepository.delete(question);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

}
