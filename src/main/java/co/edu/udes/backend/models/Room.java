package co.edu.udes.backend.models;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class Room {

    private int capacity, number, floor;
    private char building;
    private List<AcademicResource> resources;

}
