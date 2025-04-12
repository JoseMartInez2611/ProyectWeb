package co.edu.udes.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoomDTO {
    private Long id;
    private int capacity;
    private String number;
    private String floor;
    private char building;
    private List<AcademicResourceDTO> resources;
}
