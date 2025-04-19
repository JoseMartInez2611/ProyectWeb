package co.edu.udes.backend.services;


import co.edu.udes.backend.dto.BorrowDTO;
import co.edu.udes.backend.mappers.BorrowMapper;
import co.edu.udes.backend.models.Borrow;
import co.edu.udes.backend.repositories.BorrowRepository;
import co.edu.udes.backend.repositories.LessonRepository;
import co.edu.udes.backend.repositories.ScheduleRepository;
import co.edu.udes.backend.utils.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BorrowService {

    @Autowired
    private BorrowMapper borrowMapper;
    private final ScheduleRepository scheduleRepository;
    private final BorrowRepository borrowRepository;
    private final LessonRepository lessonRepository;

    public List<BorrowDTO> getAll() {
        List<Borrow> borrows = borrowRepository.findAll();
        return borrowMapper.toDtoList(borrows);
    }


    public BorrowDTO create(Borrow borrow) {
        isBorrowed(borrow, null);
        return borrowMapper.toDto(borrowRepository.save(borrow));
    }

    public List<BorrowDTO> createMultiple(List<Borrow> borrows) {
        List<BorrowDTO> newBorrow = new ArrayList<>();
        for (Borrow borrow : borrows) {
            newBorrow.add(create(borrow));
        }
        return newBorrow;
    }


//    public List<BorrowDTO> createMultiple(List<Borrow> list) {
//        getValidation(list);
//        return borrowMapper.toDtoList(
//                borrowRepository.saveAll(list)
//        );
//    }

    public BorrowDTO getById(Long id) {
        return borrowMapper.toDto(borrowRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Borrow not found with id: " + id)));
    }


    public BorrowDTO update(Long id, Borrow borrow) {
        borrowRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Borrow not found with id: " + id));
        borrow.setId(id);
        return borrowMapper.toDto(borrowRepository.save(borrow));
    }

    public void delete(Long id) {
        borrowRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Borrow not found with id: " + id));
        borrowRepository.deleteById(id);
    }


    //Recive el objeto lesson y el ID para actualizar
    public void isBorrowed(Borrow borrow, Long excludeId) {

        List<Borrow> borrows = borrowRepository.findAllByResource_Id(borrow.getResource().getId());

        for (Borrow existingBorrow : borrows) {
            if (excludeId != null && existingBorrow.getId().equals(excludeId)) {
                continue;
            }


            boolean startsBefore = borrow.getBorrowDate().isBefore(existingBorrow.getReturnDate());
            boolean endsAfter = borrow.getReturnDate().isAfter(existingBorrow.getBorrowDate());

            if (startsBefore && endsAfter) {
                throw new RuntimeException(
                        "The resource is already borrowed by another student."
                );
            }
        }
    }

}