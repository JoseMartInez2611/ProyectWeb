package co.edu.udes.backend.controllers;

import co.edu.udes.backend.models.inheritance.Evaluation;
import co.edu.udes.backend.repositories.EvaluationRepository;
import co.edu.udes.backend.utils.ResourceNotFoundException;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
public class EvaluationController {
    @Autowired
    private EvaluationRepository evaluationRepository;

    //get all evaluations
    @GetMapping
    public List<Evaluation> getAllEvaluations(@RequestBody Evaluation evaluation){
        return evaluationRepository.findAll();
    }

    //Create evaluation
    @PostMapping("/evaluations")
    public Evaluation createEvaluation(@RequestBody Evaluation evaluation){
        return evaluationRepository.save(evaluation);
    }

    //get evaluation by id
    @GetMapping("/evaluations/{id}")
    public ResponseEntity<Evaluation> getEvaluationById(@PathVariable Long id){
        Evaluation evaluation = evaluationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Evaluation not exist with id :" + id));
        return ResponseEntity.ok(evaluation);
    }

    //update evaluation
    @PutMapping("/evaluations/{id}")
    public ResponseEntity<Evaluation> updateEvaluation(@PathVariable Long id, @RequestBody Evaluation evaluation){
        Evaluation existingEvaluation = evaluationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Evaluation not exist with id :" + id));

        existingEvaluation.setEvaluationRubric(evaluation.getEvaluationRubric());
        existingEvaluation.setId(evaluation.getId());
        existingEvaluation.setGroup(evaluation.getGroup());

        Evaluation updatedEvaluation = evaluationRepository.save(evaluation);
        return ResponseEntity.ok(updatedEvaluation);
    }

    //delete evaluation
    @DeleteMapping("/evaluations/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteEvaluation(@PathVariable Long id){
        Evaluation evaluation = evaluationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Evaluation not exist with id :" + id));
        evaluationRepository.delete(evaluation);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
