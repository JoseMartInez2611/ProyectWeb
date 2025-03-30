package co.edu.udes.backend.models;

import java.util.List;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "courses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "credits")
    private int credits;

    @ManyToMany
    @JoinTable(
        name = "course_prerequisites",
        joinColumns = @JoinColumn(name = "course_id"),
        inverseJoinColumns = @JoinColumn(name = "prerequisite_id")
    )
    private List<Course> prerequisites;

    @Column(name = "theorical_hours")
    private int teoricalHours;

    @Column(name = "practical_hours")
    private int practicalHours;

    @ElementCollection
    @CollectionTable(name = "course_objectives", joinColumns = @JoinColumn(name = "course_id"))
    private List<String> objectives;

    @ElementCollection
    @CollectionTable(name = "course_content", joinColumns = @JoinColumn(name = "course_id"))
    private List<String> content;

    @ElementCollection
    @CollectionTable(name = "course_competences", joinColumns = @JoinColumn(name = "course_id"))
    private List<String> competences;
}
