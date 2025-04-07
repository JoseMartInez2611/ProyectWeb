package co.edu.udes.backend.controllers;

import co.edu.udes.backend.dto.DayOfWeekDTO;
import co.edu.udes.backend.services.DayOfWeekService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@CrossOrigin(origin = "http://localhost")
@RestController
@RequestMapping("/api/v1/days-of-week")
public class DayOfWeekController {

    @Autowired
    private DayOfWeekService dayOfWeekService;

    // get all days of the week
    @GetMapping
    public ResponseEntity<List<DayOfWeekDTO>> getAllDaysOfWeek() {
        return ResponseEntity.ok(dayOfWeekService.getAll());
    }

    // create day of the week rest api
    @PostMapping
    public ResponseEntity<DayOfWeekDTO> create(@RequestBody DayOfWeekDTO dto) {
        return ResponseEntity.ok(dayOfWeekService.create(dto));
    }

    // get day of the week by id rest api
    @GetMapping("/{id}")
    public ResponseEntity<DayOfWeekDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(dayOfWeekService.getById(id));
    }

    // update day of the week rest api
    @PutMapping("/{id}")
    public ResponseEntity<DayOfWeekDTO> update(@PathVariable Long id, @RequestBody DayOfWeekDTO dto) {
        return ResponseEntity.ok(dayOfWeekService.update(id, dto));
    }

    // delete day of the week rest api
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> delete(@PathVariable Long id) {
        dayOfWeekService.delete(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
