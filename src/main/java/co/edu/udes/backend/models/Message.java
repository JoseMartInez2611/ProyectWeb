package co.edu.udes.backend.models;


import co.edu.udes.backend.models.inheritance.Communication;
import co.edu.udes.backend.models.inheritance.User;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table (name = "message")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@SuperBuilder

public class Message extends Communication {

    @Column(name = "subject", nullable = false, columnDefinition = "varchar(255)")
    private String subject;

    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false, referencedColumnName = "id")
    private User sender;
}
