package co.edu.udes.backend.models.inheritance;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table (name = "communication")
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
@Inheritance(strategy = InheritanceType.JOINED)

public class Communication {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;

    private List<User> receiver;

    @Column(name = "sent_date",nullable=false, columnDefinition = "DATE")
    private LocalDate sentDate;

    @Column(name = "content",nullable=false, columnDefinition = "varchar(255)")
    private String content;

    @Column(name = "read", nullable = false, columnDefinition = "boolean")
    private boolean read = false;

    public void markAsRead() {
        this.read = true;
    }

}