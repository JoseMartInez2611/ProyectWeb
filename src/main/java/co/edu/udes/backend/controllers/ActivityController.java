package co.edu.udes.backend.controllers;

import co.edu.udes.backend.dto.ActivityDTO;
import co.edu.udes.backend.mappers.ActivityMapper;
import co.edu.udes.backend.models.Activity;
import co.edu.udes.backend.services.ActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/activity")
@RequiredArgsConstructor
public class ActivityController {

    @Autowired
    private final ActivityService activityService;

    @Autowired
    private final ActivityMapper activityMapper;

    @GetMapping
    public ResponseEntity<List<ActivityDTO>> getAll() {
        return ResponseEntity.ok(activityService.getAll());

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try{
            return ResponseEntity.ok().body(activityService.getById(id));
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ActivityDTO dto) {
        try{
            Activity activity = activityMapper.toEntity(dto);
            return ResponseEntity.ok(activityService.create(activity));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Please check the data you are sending");
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody ActivityDTO dto) {
        try{
            Activity activity = activityMapper.toEntity(dto);
            return ResponseEntity.ok(activityService.update(id, activity));
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Please check the data you are sending");
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            activityService.delete(id);
            return ResponseEntity.noContent().build();
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}
