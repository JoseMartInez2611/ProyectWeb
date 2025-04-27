package co.edu.udes.backend.models.inheritance;

import co.edu.udes.backend.models.Borrow;
import co.edu.udes.backend.models.Message;
import co.edu.udes.backend.models.Report;
import co.edu.udes.backend.models.RoleEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


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

    @ToString.Exclude
    @OneToMany(
            targetEntity = Message.class,
            fetch = FetchType.LAZY,
            mappedBy = "sender"
    )
    private List<Message> message;

    @ToString.Exclude
    @OneToMany(
            targetEntity = Report.class,
            fetch = FetchType.LAZY,
            mappedBy = "requestingProfileU"
    )
    private List<Report> report;

    @ToString.Exclude
    @OneToMany(
            targetEntity = Borrow.class,
            fetch = FetchType.LAZY,
            mappedBy = "petitioner"
    )
    private List<Borrow> borrow;

    @ToString.Exclude
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "receivers")
    private List<Communication> communication;

    @Column(name="is_enable")
    private boolean isEnable;

    @Column(name="account_non_expired")
    private boolean accountNonExpired;

    @Column(name="account_non_locked")
    private boolean accountNonLocked;

    @Column(name="credentials_non_expired")
    private boolean credentialsNonExpired;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "profileU_roles",
            joinColumns = @JoinColumn(name = "profileU_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<RoleEntity> roles = new HashSet<>();
}
