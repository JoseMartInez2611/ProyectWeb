package co.edu.udes.backend.models.inheritance;

import co.edu.udes.backend.models.Borrow;
import co.edu.udes.backend.models.Message;
import co.edu.udes.backend.models.Report;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@Table(name="profileU")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
@ToString(includeFieldNames = false)
@EqualsAndHashCode
@SuperBuilder

public class ProfileU {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(name="first_name", columnDefinition = "VARCHAR(255)", nullable = false)
    String firstName;

    @Column(name="last_name", columnDefinition = "VARCHAR(255)", nullable = false)
    String lastName;

    @Column(name="phone", columnDefinition = "VARCHAR(12)", nullable = false)
    String phone;

    @Column(name="email", columnDefinition = "VARCHAR(255)", nullable = false)
    String email;

    @Column(name="user_name", columnDefinition = "VARCHAR(255)", nullable = false)
    String userName;

    @Column(name="password", columnDefinition = "VARCHAR(255)", nullable = false)
    String password;

    @OneToMany(
            targetEntity = Message.class,
            fetch = FetchType.LAZY,
            mappedBy = "sender"
    )
    List<Message> message;

    @OneToMany(
            targetEntity = Report.class,
            fetch = FetchType.LAZY,
            mappedBy = "requestingProfileU"
    )
    List<Report> report;

    @OneToMany(
            targetEntity = Borrow.class,
            fetch = FetchType.LAZY,
            mappedBy = "petitioner"
    )
    List<Borrow> borrow;

    @ManyToMany(
            targetEntity = Communication.class,
            fetch = FetchType.LAZY
    )
    @JoinTable(joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "communication_id"))
    List<Communication> communication;

}
