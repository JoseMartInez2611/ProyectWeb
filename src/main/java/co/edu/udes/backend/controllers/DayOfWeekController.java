package co.edu.udes.backend.controllers;

import co.edu.udes.backend.models.DayOfWeek;
import co.edu.udes.backend.repositories.DayOfWeekRepository;
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
public class DayOfWeekController {

    @Autowired
    private DayOfWeekRepository dayOfWeekRepository;

    // get all days of the week
    @GetMapping("days-of-week")
    public List<DayOfWeek> getAllDaysOfWeek() {
        return dayOfWeekRepository.findAll();
    }

    // create day of the week rest api
    @PostMapping("days-of-week")
    public DayOfWeek createDayOfWeek(@RequestBody DayOfWeek dayOfWeek) {
        return dayOfWeekRepository.save(dayOfWeek);
    }

    // get day of the week by id rest api
    @GetMapping("days-of-week/{id}")
    public ResponseEntity<DayOfWeek> getDayOfWeekById(@PathVariable Long id) {
        DayOfWeek dayOfWeek = dayOfWeekRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Day of the week not exist with id :" + id));
        return ResponseEntity.ok(dayOfWeek);
    }

    // update day of the week rest api
    @PutMapping("days-of-week/{id}")
    public ResponseEntity<DayOfWeek> updateDayOfWeek(@PathVariable Long id, @RequestBody DayOfWeek dayOfWeekDetails) {
        DayOfWeek dayOfWeek = dayOfWeekRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Day of the week not exist with id :" + id));

        dayOfWeek.setName(dayOfWeekDetails.getName());

        DayOfWeek updatedDayOfWeek = dayOfWeekRepository.save(dayOfWeek);
        return ResponseEntity.ok(updatedDayOfWeek);
    }

    // delete day of the week rest api
    @DeleteMapping("days-of-week/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteDayOfWeek(@PathVariable Long id){
        DayOfWeek dayOfWeek = dayOfWeekRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Day of the week not exist with id :" + id));

        dayOfWeekRepository.delete(dayOfWeek);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
