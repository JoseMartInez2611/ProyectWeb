package co.edu.udes.backend.services;

import co.edu.udes.backend.dto.FinalNoteDTO;
import co.edu.udes.backend.mappers.FinalNoteMapper;
import co.edu.udes.backend.repositories.FinalNoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FinalNoteService {

    private final FinalNoteRepository finalNoteRepository;
    private final FinalNoteMapper finalNoteMapper;

    public List<FinalNoteDTO> getAll() {
        return finalNoteRepository.findAll().stream()
                .map(finalNoteMapper::toDTO)
                .toList();
    }

    public FinalNoteDTO getById(Long id) {
        return finalNoteMapper.toDTO(finalNoteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Final note not found with id: " + id)));
    }

    public FinalNoteDTO create(FinalNoteDTO dto) {
        return finalNoteMapper.toDTO(finalNoteRepository.save(finalNoteMapper.toEntity(dto)));
    }

    public FinalNoteDTO update(Long id, FinalNoteDTO dto) {
        finalNoteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Final note not found with id: " + id));
        dto.setId(id);
        return finalNoteMapper.toDTO(finalNoteRepository.save(finalNoteMapper.toEntity(dto)));
    }

    public void delete(Long id) {
        finalNoteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Final note not found with id: " + id));
        finalNoteRepository.deleteById(id);
    }
}
