package co.edu.udes.backend.controllers;

import co.edu.udes.backend.dto.AcademicSubperiodDTO;
import co.edu.udes.backend.dto.QualificationCategoryDTO;
import co.edu.udes.backend.mappers.AcademicSubperiodMapper;
import co.edu.udes.backend.mappers.QualificationCategoryMapper;
import co.edu.udes.backend.models.AcademicSubperiod;
import co.edu.udes.backend.models.QualificationCategory;
import co.edu.udes.backend.services.AcademicSubperiodService;
import co.edu.udes.backend.services.QualificationCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/qualification-categories")
public class QualificationCategoryController {

    @Autowired
    private QualificationCategoryService qualificationCategoryService;

    @Autowired
    private QualificationCategoryMapper qualificationCategoryMapper;

    // get all qualification categories
    @GetMapping
    public ResponseEntity<List<QualificationCategoryDTO>> getAllAcademicSubperiods() {
        return ResponseEntity.ok(qualificationCategoryService.getAll());
    }

    // create qualification category rest api
    @PostMapping
    public ResponseEntity<?> create(@RequestBody List<QualificationCategoryDTO> dtos){
        try{
            List<QualificationCategory> entities = qualificationCategoryMapper.toEntityList(dtos);
            return ResponseEntity.ok(qualificationCategoryService.createMultiple(entities));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Please check the data you are sending" + e.getMessage());
        }
    }

    // get qualification category by id rest api
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try{
            return ResponseEntity.ok().body(qualificationCategoryService.getById(id));
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // update qualification category rest api
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody QualificationCategoryDTO dto) {
        try{
            QualificationCategory qualificationCategory = qualificationCategoryMapper.toEntity(dto);
            return ResponseEntity.ok(qualificationCategoryService.update(id, qualificationCategory));
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Please check the data you are sending");
        }
    }

    // delete qualification category rest api
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            qualificationCategoryService.delete(id);
            return ResponseEntity.noContent().build();
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
