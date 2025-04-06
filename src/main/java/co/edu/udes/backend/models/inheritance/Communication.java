package co.edu.udes.backend.models.inheritance;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
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

public class Communication {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(mappedBy = "communication", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<User> receiver;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "sent_date")
    private Date sentDate;

    @Column(name = "content")
    private String content;

    @Column(name = "read")
    private boolean read = false;

    public void markAsRead() {
        this.read = true;
    }

}
