package co.edu.udes.backend.models.inheritance;

import co.edu.udes.backend.models.Borrow;
import co.edu.udes.backend.models.Message;
import co.edu.udes.backend.models.Report;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name="profileU")

public class ProfileU {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="first_name", columnDefinition = "VARCHAR(255)", nullable = false)
    private String firstName;

    @Column(name="last_name", columnDefinition = "VARCHAR(255)", nullable = false)
    private String lastName;

    @Column(name="phone", columnDefinition = "VARCHAR(12)", nullable = false)
    private String phone;

    @Column(name="email", columnDefinition = "VARCHAR(255)", nullable = false)
    private String email;

    @Column(name="user_name", columnDefinition = "VARCHAR(255)", nullable = false)
    private String userName;

    @Column(name="password", columnDefinition = "VARCHAR(255)", nullable = false)
    private String password;

    @OneToMany(
            targetEntity = Message.class,
            fetch = FetchType.LAZY,
            mappedBy = "sender"
    )
    private List<Message> message;

    @OneToMany(
            targetEntity = Report.class,
            fetch = FetchType.LAZY,
            mappedBy = "requestingProfileU"
    )
    private List<Report> report;

    @OneToMany(
            targetEntity = Borrow.class,
            fetch = FetchType.LAZY,
            mappedBy = "petitioner"
    )
    private List<Borrow> borrow;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "receivers")
    private List<Communication> communication;

}
