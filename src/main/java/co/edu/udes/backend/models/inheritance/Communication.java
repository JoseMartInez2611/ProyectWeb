package co.edu.udes.backend.models.inheritance;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table (name = "communication")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@SuperBuilder
@Inheritance(strategy = InheritanceType.JOINED)

public class Communication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToMany(mappedBy = "communication", fetch = FetchType.LAZY)
    private List<ProfileU> receiver;

    @Column(name = "sent_date", nullable = false, columnDefinition = "DATE")
    private LocalDate sentDate;

    @Column(name = "content", nullable = false, columnDefinition = "varchar(255)")
    private String content;

    @Column(name = "read", nullable = false, columnDefinition = "boolean")
    private boolean read = false;

}

