package co.edu.udes.backend.services;


import co.edu.udes.backend.dto.BorrowDTO;
import co.edu.udes.backend.mappers.BorrowMapper;
import co.edu.udes.backend.models.Borrow;
import co.edu.udes.backend.repositories.BorrowRepository;
import co.edu.udes.backend.utils.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BorrowService {

    private final BorrowRepository borrowRepository;

    public List<BorrowDTO> getAll() {
        List<Borrow> borrows = borrowRepository.findAll();
        return BorrowMapper.INSTANCE.toDtoList(borrows);
    }

    public BorrowDTO getById(Long id) {
        return BorrowMapper.INSTANCE.toDto(borrowRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Borrow not found with id: " + id)));
    }

    public BorrowDTO create(Borrow borrow) {
        return BorrowMapper.INSTANCE.toDto(borrowRepository.save(borrow));
    }

    public BorrowDTO update(Long id, Borrow borrow) {
        borrowRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Borrow not found with id: " + id));
        borrow.setId(id);
        return BorrowMapper.INSTANCE.toDto(borrowRepository.save(borrow));
    }

    public void delete(Long id) {
        borrowRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Borrow not found with id: " + id));
        borrowRepository.deleteById(id);
    }
}