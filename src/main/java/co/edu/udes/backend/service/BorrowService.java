package co.edu.udes.backend.service;

import co.edu.udes.backend.models.Borrow;
import co.edu.udes.backend.repositories.BorrowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BorrowService {

    private final BorrowRepository borrowRepository;

    @Autowired
    public BorrowService(BorrowRepository borrowRepository) {
        this.borrowRepository = borrowRepository;
    }

    public List<Borrow> getAllBorrows() {
        return borrowRepository.findAll();
    }

    public Optional<Borrow> getBorrowById(Long id) {
        return borrowRepository.findById(id);
    }

    public Borrow saveBorrow(Borrow borrow) {
        return borrowRepository.save(borrow);
    }

    public void deleteBorrow(Long id) {
        borrowRepository.deleteById(id);
    }

    // Add other business logic methods (e.g., find by user, find overdue borrows)
}