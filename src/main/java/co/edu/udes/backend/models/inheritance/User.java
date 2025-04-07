package co.edu.udes.backend.models.inheritance;

import co.edu.udes.backend.models.Borrow;
import co.edu.udes.backend.models.Message;
import co.edu.udes.backend.models.Report;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@Table(name="user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
@ToString(includeFieldNames = false)
@EqualsAndHashCode
@SuperBuilder

public class User {

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
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            mappedBy = "user"
    )
    Message message;

    @OneToMany(
            targetEntity = Report.class,
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            mappedBy = "user"
    )
    Report report;

    @OneToMany(
            targetEntity = Borrow.class,
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            mappedBy = "user"
    )
    Borrow borrow;

    @ManyToMany(
            targetEntity = Communication.class,
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    @JoinTable(joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "communication_id"))
    Communication communication;

}
