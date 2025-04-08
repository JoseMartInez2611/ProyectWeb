package co.edu.udes.backend.mappers;

import co.edu.udes.backend.dto.RoomDTO;
import co.edu.udes.backend.models.Room;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface RoomMapper {
    RoomMapper INSTANCE = Mappers.getMapper(RoomMapper.class);

    Room toEntity(RoomDTO room);
    List<Room> toEntityList(List<RoomDTO> rooms);

    RoomDTO toDto(Room room);
    List<RoomDTO> toDtoList(List<Room> rooms);
}