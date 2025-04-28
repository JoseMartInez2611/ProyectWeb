package co.edu.udes.backend.models.inheritance;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table (name = "communication")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Inheritance(strategy = InheritanceType.JOINED)

public class Communication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "communication_profileU",
            inverseJoinColumns= @JoinColumn(name = "user_id"),
            joinColumns = @JoinColumn(name = "communication_id")
    )
    private List<ProfileU> receivers;

    @Column(name = "sent_date", nullable = false, columnDefinition = "DATE")
    private LocalDateTime sentDate;

    @Column(name = "content", nullable = false, columnDefinition = "varchar(255)")
    private String content;

    @Column(name = "read", nullable = false, columnDefinition = "boolean")
    private boolean read = false;

}

