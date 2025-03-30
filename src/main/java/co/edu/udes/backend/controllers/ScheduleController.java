package co.edu.udes.backend.controllers;

import co.edu.udes.backend.models.Schedule;
import co.edu.udes.backend.repositories.ScheduleRepository;
import co.edu.udes.backend.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


//@CrossOrigin(origin = "http://localhost")
@RestController
@RequestMapping("/api/v1/")
public class ScheduleController {

    @Autowired
    private ScheduleRepository scheduleRepository;

    // get all schedules
    @GetMapping("/schedules")
    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    // create schedule rest api
    @PostMapping("/schedules")
    public Schedule createSchedule(@RequestBody Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    // get schedule by id rest api
    @GetMapping("/schedules/{id}")
    public ResponseEntity<Schedule> getScheduleById(@PathVariable Long id) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Schedule not exist with id :" + id));
        return ResponseEntity.ok(schedule);
    }

    // update schedule rest api
    @PutMapping("/schedules/{id}")
    public ResponseEntity<Schedule> updateSchedule(@PathVariable Long id, @RequestBody Schedule scheduleDetails) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Schedule not exist with id :" + id));

        schedule.setStartHour(scheduleDetails.getStartHour());
        schedule.setEndHour(scheduleDetails.getEndHour());
        schedule.setDayOfWeek(scheduleDetails.getDayOfWeek());

        Schedule updatedSchedule = scheduleRepository.save(schedule);
        return ResponseEntity.ok(updatedSchedule);
    }

    // delete schedule rest api
    @DeleteMapping("/schedules/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteSchedule(@PathVariable Long id) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Schedule not exist with id :" + id));

        scheduleRepository.delete(schedule);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
