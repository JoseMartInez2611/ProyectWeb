package co.edu.udes.backend.mappers;

import co.edu.udes.backend.dto.BorrowDTO;
import co.edu.udes.backend.models.Borrow;
import org.mapstruct.factory.Mappers;

import java.util.List;

public interface BorrowMapper {
    BorrowMapper INSTANCE = Mappers.getMapper(BorrowMapper.class);

    Borrow toEntity(BorrowDTO borrow);
    List<Borrow> toEntityList(List<BorrowDTO> borrows);

    BorrowDTO toDto(Borrow borrow);
    List<BorrowDTO> toDtoList(List<Borrow> borrows);
}