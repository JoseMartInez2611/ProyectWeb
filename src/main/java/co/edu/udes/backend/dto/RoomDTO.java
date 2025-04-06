package co.edu.udes.backend.dto;

import lombok.Data;
import lombok.Builder;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class RoomDTO {
    private Long id;
    private int capacity;
    private String number;
    private String floor;
    private char building;
    private List<Long> resourceIds; // List of IDs of the AcademicResources in the room
}
