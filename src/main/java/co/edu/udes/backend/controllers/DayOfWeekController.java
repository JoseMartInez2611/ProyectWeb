package co.edu.udes.backend.controllers;

import co.edu.udes.backend.dto.DayOfWeekDTO;
import co.edu.udes.backend.mappers.DayOfWeekMapper;
import co.edu.udes.backend.models.DayOfWeek;
import co.edu.udes.backend.services.DayOfWeekService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin(origin = "http://localhost")
@RestController
@RequestMapping("/api/v1/days-of-week")
public class DayOfWeekController {

    @Autowired
    private DayOfWeekService dayOfWeekService;

    @Autowired
    private DayOfWeekMapper dayOfWeekMapper;

    // get all days of the week
    @GetMapping
    public ResponseEntity<List<DayOfWeekDTO>> getAllDaysOfWeek() {
        return ResponseEntity.ok(dayOfWeekService.getAll());
    }

    // create day of the week rest api
    @PostMapping
    public ResponseEntity<?> create(@RequestBody List<DayOfWeekDTO> dtos){
        try{
            List<DayOfWeek> entities = dayOfWeekMapper.toEntityList(dtos);
            return ResponseEntity.ok(dayOfWeekService.createMultiple(entities));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Please check the data you are sending" + e.getMessage());
        }
    }

    // get day of the week by id rest api
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try{
            return ResponseEntity.ok().body(dayOfWeekService.getById(id));
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // update day of the week rest api
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody DayOfWeekDTO dto) {
        try{
            DayOfWeek dayOfWeek = dayOfWeekMapper.toEntity(dto);
            return ResponseEntity.ok(dayOfWeekService.update(id, dayOfWeek));
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Please check the data you are sending");
        }
    }

    // delete day of the week rest api
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            dayOfWeekService.delete(id);
            return ResponseEntity.noContent().build();
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
