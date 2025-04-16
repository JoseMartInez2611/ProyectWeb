package co.edu.udes.backend.mappers;

import co.edu.udes.backend.dto.BorrowDTO;
import co.edu.udes.backend.models.Borrow;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring",
        uses = {AcademicResourceMapper.class,
                EmployeeMapper.class
        }
)

public interface BorrowMapper {
    BorrowMapper INSTANCE = Mappers.getMapper(BorrowMapper.class);

    @Mapping(source = "resourceId", target = "resource.id")
    @Mapping(source = "petitionerId", target = "petitioner.id")
    Borrow toEntity(BorrowDTO borrow);
    List<Borrow> toEntityList(List<BorrowDTO> borrows);

    @Mapping(source = "resource.id", target = "resourceId")
    @Mapping(source = "petitioner.id", target = "petitionerId")
    BorrowDTO toDto(Borrow borrow);
    List<BorrowDTO> toDtoList(List<Borrow> borrows);
}