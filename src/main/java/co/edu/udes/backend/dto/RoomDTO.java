package co.edu.udes.backend.dto;

import lombok.Data;
import lombok.Builder;

import java.util.List;

@Data
@Builder
public class RoomDTO {
    private Long id;
    private int capacity;
    private String number;
    private String floor;
    private char building;
    private List<AcademicResourceDTO> resources;
}
