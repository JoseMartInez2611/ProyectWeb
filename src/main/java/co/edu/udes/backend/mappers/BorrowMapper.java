package co.edu.udes.backend.mappers;

import co.edu.udes.backend.dto.BorrowDTO;
import co.edu.udes.backend.dto.EmployeeDTO;
import co.edu.udes.backend.models.Borrow;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BorrowMapper {

    private final AcademicResourceMapper academicResourceMapper;
    private final EmployeeMapper employeeMapper;

    public BorrowMapper(AcademicResourceMapper academicResourceMapper, EmployeeMapper employeeMapper) {
        this.academicResourceMapper = academicResourceMapper;
        this.employeeMapper = employeeMapper;
    }

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
        Borrow borrow = new Borrow();
        borrow.setId(borrowDTO.getId());
        borrow.setBorrowDate(borrowDTO.getBorrowDate());
        borrow.setReturnDate(borrowDTO.getReturnDate());
        borrow.setDuration(borrowDTO.getDuration());
        borrow.setResource(academicResourceMapper.toEntity(borrowDTO.getResource()));
        borrow.setLender(employeeMapper.toEntity(borrowDTO.getLender()));
        return borrow;
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