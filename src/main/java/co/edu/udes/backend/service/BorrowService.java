package co.edu.udes.backend.service;


import co.edu.udes.backend.dto.BorrowDTO;
import co.edu.udes.backend.mapper.BorrowMapper;
import co.edu.udes.backend.models.Borrow;
import co.edu.udes.backend.repositories.BorrowRepository;
import co.edu.udes.backend.utils.ResourceNotFoundException;
import co.edu.udes.backend.models.AcademicResource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BorrowService {

    private final BorrowRepository entityNameRepository;
    private final BorrowMapper entityNameMapper;

    public List<BorrowDTO> getAll() {
        return entityNameRepository.findAll().stream()
                .map(entityNameMapper::toDTO)
                .collect(Collectors.toList());
    }

    public BorrowDTO getById(Long id) {
        Borrow entity = entityNameRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Entity not found with id: " + id));
        return entityNameMapper.toDTO(entity);
    }

    public BorrowDTO create(BorrowDTO dto) {
        Borrow entity = entityNameMapper.toEntity(dto);
        return entityNameMapper.toDTO(entityNameRepository.save(entity));
    }

    public BorrowDTO update(Long id, BorrowDTO dto) {
        entityNameRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Entity not found with id: " + id));
        dto.setId(id);
        Borrow updated = entityNameRepository.save(entityNameMapper.toEntity(dto));
        return entityNameMapper.toDTO(updated);
    }

    public void delete(Long id) {
        Borrow entity = entityNameRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Entity not found with id: " + id));
        entityNameRepository.delete(entity);
    }
}