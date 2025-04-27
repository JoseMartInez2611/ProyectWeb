package co.edu.udes.backend.controllers;



import co.edu.udes.backend.dto.inheritanceDTO.ProfileUDTO;
import co.edu.udes.backend.mappers.ProfileUMapper;
import co.edu.udes.backend.models.inheritance.ProfileU;
import co.edu.udes.backend.services.ProfileUService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class ProfileUController {

    @Autowired
    private final ProfileUService profileUService;

    @Autowired
    private final ProfileUMapper profileUMapper;

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
    public ResponseEntity<?> create(@RequestBody List<ProfileUDTO> dtoList) {
        try {
            List<ProfileU> profileUList = profileUMapper.toEntityList(dtoList);
            List<ProfileU> createdUsers = profileUService.createMultiple(profileUList); // Modifica el servicio
            return ResponseEntity.ok(profileUMapper.toDtoList(createdUsers)); // Devuelve DTOs
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Please check the data you are sending: " + e.getMessage()); // Incluye el mensaje de error
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody ProfileUDTO dto) {
        try{
            ProfileU profileU = profileUMapper.toEntity(dto);
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
