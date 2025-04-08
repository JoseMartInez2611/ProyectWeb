package co.edu.udes.backend.mappers;

import co.edu.udes.backend.dto.RoomDTO;
import co.edu.udes.backend.models.Room;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RoomMapper {

    private AcademicResourceMapper academicResourceMapper;

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
                .resources(academicResourceMapper.toDTOList(room.getResources()))
                .build();
    }

    public Room toEntity(RoomDTO roomDTO) {
        if (roomDTO == null) {
            return null;
        }
        return Room.builder()
                .id(roomDTO.getId())
                .capacity(roomDTO.getCapacity())
                .number(roomDTO.getNumber())
                .floor(roomDTO.getFloor())
                .building(roomDTO.getBuilding())
                .resources(academicResourceMapper.toEntityList(roomDTO.getResources()))
                .build();
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