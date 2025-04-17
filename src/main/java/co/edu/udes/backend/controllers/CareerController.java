package co.edu.udes.backend.controllers;

import co.edu.udes.backend.dto.CareerDTO;
import co.edu.udes.backend.mappers.CareerMapper;
import co.edu.udes.backend.models.Career;
import co.edu.udes.backend.services.CareerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/career")
public class CareerController {


    @Autowired
    private CareerService careerService;

    @Autowired
    private CareerMapper careerMapper;

    // Get all absence justifications
    @GetMapping
    public ResponseEntity<List<CareerDTO>> getAllCareer() {
        return ResponseEntity.ok(careerService.getAll());
    }

    // Create absence justification
    @PostMapping
    public ResponseEntity<?> create(@RequestBody List<CareerDTO> dtos){
        try{
            List<Career> entities = careerMapper.toEntityList(dtos);
            return ResponseEntity.ok(careerService.createMultiple(entities));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Please check the data you are sending " + e.getMessage());
        }
    }

    // Get absence justification by id
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try{
            return ResponseEntity.ok().body(careerService.getById(id));
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    // Update absence justification
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody CareerDTO dto) {
        try{
            Career career = careerMapper.toEntity(dto);
            return ResponseEntity.ok(careerService.update(id, career));
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Please check the data you are sending");
        }

    }

    // Delete absence justification
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try{
            careerService.delete(id);
            return ResponseEntity.noContent().build();
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


}
