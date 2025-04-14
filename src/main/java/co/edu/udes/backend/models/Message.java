package co.edu.udes.backend.models;


import co.edu.udes.backend.models.inheritance.Communication;
import co.edu.udes.backend.models.inheritance.ProfileU;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table (name = "message")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Builder
@EqualsAndHashCode(callSuper = true)
@ToString(includeFieldNames = false, callSuper = true)

public class Message extends Communication {

    @Column(name = "subject", nullable = false, columnDefinition = "varchar(255)")
    private String subject;

    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false, columnDefinition = "BIGINT")
    private ProfileU sender;
}
