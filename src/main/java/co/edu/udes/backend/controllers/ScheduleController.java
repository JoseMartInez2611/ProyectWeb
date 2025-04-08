package co.edu.udes.backend.controllers;

import co.edu.udes.backend.dto.ScheduleDTO;
import co.edu.udes.backend.mappers.ScheduleMapper;
import co.edu.udes.backend.models.Schedule;
import co.edu.udes.backend.services.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//@CrossOrigin(origin = "http://localhost")
@RestController
@RequestMapping("/api/v1/schedules")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private ScheduleMapper scheduleMapper;

    // get all schedules
    @GetMapping
    public ResponseEntity<List<ScheduleDTO>> getAllSchedules() {
        return ResponseEntity.ok(scheduleService.getAll());
    }

    // create schedule
    @PostMapping
    public ResponseEntity<?> create(@RequestBody ScheduleDTO dto) {
        try{
            Schedule schedule = scheduleMapper.toEntity(dto);
            return ResponseEntity.ok(scheduleService.create(schedule));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Please check the data you are sending");
        }
    }

    // get schedule by id
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try{
            return ResponseEntity.ok().body(scheduleService.getById(id));
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // update schedule
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody ScheduleDTO dto) {
        try{
            Schedule schedule = scheduleMapper.toEntity(dto);
            return ResponseEntity.ok(scheduleService.update(id, schedule));
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Please check the data you are sending");
        }
    }

    // delete schedule
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            scheduleService.delete(id);
            return ResponseEntity.noContent().build();
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
        }
}
