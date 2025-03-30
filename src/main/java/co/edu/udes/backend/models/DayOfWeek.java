package co.edu.udes.backend.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "days")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DayOfWeek {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", unique = true)
    private String name;
}
