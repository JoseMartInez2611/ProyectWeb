package co.edu.udes.backend.controllers;



import co.edu.udes.backend.dto.inheritanceDTO.ProfileUDTO;
import co.edu.udes.backend.mappers.ProfileUMapper;
import co.edu.udes.backend.models.inheritance.ProfileU;
import co.edu.udes.backend.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private final UserService profileUService;

    @GetMapping
    public ResponseEntity<List<ProfileUDTO>> getAll() {
        return ResponseEntity.ok(profileUService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try{
            return ResponseEntity.ok().body(profileUService.getById(id));
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ProfileUDTO dto) {
        try{
            ProfileU profileU = ProfileUMapper.INSTANCE.toEntity(dto);
            return ResponseEntity.ok(profileUService.create(profileU));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Please check the data you are sending");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody ProfileUDTO dto) {
        try{
            ProfileU profileU = ProfileUMapper.INSTANCE.toEntity(dto);
            return ResponseEntity.ok(profileUService.update(id, profileU));
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Please check the data you are sending");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try{
            profileUService.delete(id);
            return ResponseEntity.noContent().build();
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }
}
