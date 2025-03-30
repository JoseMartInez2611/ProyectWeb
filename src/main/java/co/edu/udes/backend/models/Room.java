package co.edu.udes.backend.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "room")
public class Room {

    private int capacity, number, floor;
    private char building;
    private List<AcademicResource> resources;

}
