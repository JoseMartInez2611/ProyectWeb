package co.edu.udes.backend.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "room")
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder

public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "capacity")
    private int capacity;

    @Column(name = "number")
    private String number;

    @Column(name = "floor")
    private String floor;

    @Column(name = "building")
    private char building;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "room_id", nullable = false)
    private List<AcademicResource> resources ;

}
