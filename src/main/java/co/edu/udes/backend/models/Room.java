package co.edu.udes.backend.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Room {

    private int capacity, number, floor;
    private char building;
    private List<AcademicResource> resources;

}
