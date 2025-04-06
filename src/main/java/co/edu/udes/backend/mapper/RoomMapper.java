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
        // Note: Setting the resources requires fetching them from the database
        // based on the resourceIds in the DTO. This is typically done in the service layer.
        // For a basic mapper, you might just set an empty list or handle it differently.
        // Example (requires an AcademicResourceService):
        // room.setResources(roomDTO.getResourceIds().stream()
        //         .map(academicResourceService::findById)
        //         .collect(Collectors.toList()));
        room.setResources(new java.util.ArrayList<>()); // Or handle in service layer
        return room;
    }
}