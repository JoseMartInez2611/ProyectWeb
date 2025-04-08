package co.edu.udes.backend.models;

import java.time.LocalTime;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "schedules")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "start_hour",
            nullable = false,
            columnDefinition = "TIME"
    )
    private LocalTime startHour;

    @Column (name = "end_hour",
            nullable = false,
            columnDefinition = "TIME"
    )
    private LocalTime endHour;

    @ManyToOne
    @JoinColumn(name = "id_day",
            nullable = false,
            columnDefinition = "BIGINT"
    )
    private DayOfWeek dayOfWeek;
}
