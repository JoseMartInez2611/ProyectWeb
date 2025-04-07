package co.edu.udes.backend.mappers;

import co.edu.udes.backend.dto.BorrowDTO;
import co.edu.udes.backend.models.Borrow;

import java.util.List;
import java.util.stream.Collectors;


public class BorrowMapper {

    private final AcademicResourceMapper academicResourceMapper = new AcademicResourceMapper();
    private final EmployeeMapper employeeMapper = new EmployeeMapper();


    public BorrowDTO toDTO(Borrow borrow) {
        if (borrow == null) {
            return null;
        }
        return BorrowDTO.builder()
                .id(borrow.getId())
                .borrowDate(borrow.getBorrowDate())
                .returnDate(borrow.getReturnDate())
                .duration(borrow.getDuration())
                .resource(academicResourceMapper.toDTO(borrow.getResource()))
                .lender(employeeMapper.toDTO(borrow.getLender()))
                .build();
    }

    public Borrow toEntity(BorrowDTO borrowDTO) {
        if (borrowDTO == null) {
            return null;
        }
        return Borrow.builder()
                .id(borrowDTO.getId())
                .borrowDate(borrowDTO.getBorrowDate())
                .returnDate(borrowDTO.getReturnDate())
                .duration(borrowDTO.getDuration())
                .resource(academicResourceMapper.toEntity(borrowDTO.getResource()))
                .lender(employeeMapper.toEntity(borrowDTO.getLender()))
                .build();
    }

    public List<BorrowDTO> toDTOList(List<Borrow> borrows) {
        if (borrows == null) {
            return null;
        }
        return borrows.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<Borrow> toEntityList(List<BorrowDTO> borrowDTOs) {
        if (borrowDTOs == null) {
            return null;
        }
        return borrowDTOs.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}