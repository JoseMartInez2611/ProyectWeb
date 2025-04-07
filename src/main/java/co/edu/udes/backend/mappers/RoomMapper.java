package co.edu.udes.backend.mappers;

import co.edu.udes.backend.dto.RoomDTO;
import co.edu.udes.backend.models.AcademicResource;
import co.edu.udes.backend.models.Room;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RoomMapper {

    public RoomDTO toDTO(Room room) {
        if (room == null) {
            return null;
        }
        return RoomDTO.builder()
                .id(room.getId())
                .capacity(room.getCapacity())
                .number(room.getNumber())
                .floor(room.getFloor())
                .building(room.getBuilding())
                .resourceIds(room.getResources().stream()
                        .map(AcademicResource::getId)
                        .collect(Collectors.toList()))
                .build();
    }

    public Room toEntity(RoomDTO roomDTO) {
        if (roomDTO == null) {
            return null;
        }
        Room room = new Room();
        room.setId(roomDTO.getId());
        room.setCapacity(roomDTO.getCapacity());
        room.setNumber(roomDTO.getNumber());
        room.setFloor(roomDTO.getFloor());
        room.setBuilding(roomDTO.getBuilding());
        room.setResources(new java.util.ArrayList<>());
        return room;
    }

    public List<RoomDTO> toDTOList(List<Room> rooms) {
        if (rooms == null) {
            return null;
        }
        return rooms.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<Room> toEntityList(List<RoomDTO> roomDTOs) {
        if (roomDTOs == null) {
            return null;
        }
        return roomDTOs.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}