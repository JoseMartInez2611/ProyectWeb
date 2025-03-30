package co.edu.udes.backend.models;

import java.time.LocalTime;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "schedules")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column (name = "start_hour")
    private LocalTime startHour;

    @Column (name = "end_hour")
    private LocalTime endHour;

    @ManyToOne
    @JoinColumn(name = "day_id", nullable = false)
    private DayOfWeek dayOfWeek;
}
