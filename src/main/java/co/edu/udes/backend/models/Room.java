package co.edu.udes.backend.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "room")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder

public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "capacity", nullable = false, columnDefinition = "int")
    private int capacity;

    @Column(name = "number", nullable = false, columnDefinition = "varchar(255)")
    private String number;

    @Column(name = "floor", nullable = false, columnDefinition = "varchar(255)")
    private String floor;

    @Column(name = "building", nullable = false, columnDefinition = "char")
    private char building;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "room_id", nullable = false)
    private List<AcademicResource> resources ;

}
