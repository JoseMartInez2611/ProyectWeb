package co.edu.udes.backend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "absence_justifications")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AbsenceJustification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "motive")
    private String motive;

    @Column(name = "description")
    private String description;

    @Column(name = "justified")
    private boolean justified;
}
