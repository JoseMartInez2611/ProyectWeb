package co.edu.udes.backend.controllers;

import co.edu.udes.backend.models.Activity;
import co.edu.udes.backend.repositories.ActivityRepository;
import co.edu.udes.backend.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
public class ActivityController {

    @Autowired
    private ActivityRepository activityRepository;

    //get all activities
    @GetMapping
    public List<Activity> getAllActivities(@RequestBody Activity activity){
        return activityRepository.findAll();
    }

    //create activity
    @PostMapping("/activities")
    public Activity createActivity(@RequestBody Activity activity){
        return activityRepository.save(activity);
    }

    //get activity by id
    @GetMapping("/actvitys/{id}")
    public ResponseEntity<Activity> getActivityById(@PathVariable Long id){
        Activity activity = activityRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("activity not exist with id: "+id));
        return ResponseEntity.ok(activity);
    }

    //update activity
    @PutMapping("/activities/{id}")
    public ResponseEntity<Activity> updateActivity(@PathVariable Long id, @RequestBody Activity activity){
        Activity existingActivity = activityRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("activity not exist with id: "+id));

        existingActivity.setEvaluationRubric(activity.getEvaluationRubric());
        existingActivity.setDate(activity.getDate());
        existingActivity.setGroup(activity.getGroup());

        existingActivity.setDescription(activity.getDescription());
        existingActivity.setAnswerText(activity.getAnswerText());
        existingActivity.setAnswerDocuments(activity.getAnswerDocuments());

        Activity updatedActivity = activityRepository.save(existingActivity);
        return ResponseEntity.ok(updatedActivity);
    }

    //delete activity
    @DeleteMapping("/activities/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteUser(@PathVariable Long id){
        Activity activity = activityRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("activity not exist with id: "+id));
        activityRepository.delete(activity);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

}
