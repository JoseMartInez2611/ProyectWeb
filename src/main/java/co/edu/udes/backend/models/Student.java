package co.edu.udes.backend.models;

import co.edu.udes.backend.models.inheritance.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "student")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Student extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "semester")
    private int semester;

    @Column(name = "code")
    private String code;

    @Column(name="date_birth")
    private Date dateBirth;

    @Column(name="address")
    private String address;

    @Column(name="career")
    private String career;

    public void cancelRegistration(){}
    public void checkAcademicHistory(){}
    public void consultAssistance(){}
    public void enRoll(){}
}
