package co.edu.udes.backend.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "days")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DayOfWeek {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "day",
            unique = true,
            nullable = false,
            columnDefinition = "VARCHAR(255)"
    )
    private String day;
}
