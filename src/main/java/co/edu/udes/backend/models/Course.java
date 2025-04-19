package co.edu.udes.backend.models;

import java.util.List;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "courses")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name",
            unique = true,
            nullable = false,
            columnDefinition = "VARCHAR(255)"
    )
    private String name;

    @Column(name = "credits",
            nullable = false,
            columnDefinition = "INT"
    )
    private int credits;

    @Column(name = "theoretical_hours",
            nullable = false,
            columnDefinition = "INT"
    )
    private int theoreticalHours;

    @Column(name = "practical_hours",
            nullable = false,
            columnDefinition = "INT"
    )
    private int practicalHours;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "course_prerequisites",
            joinColumns = @JoinColumn(name = "id_course"),
            inverseJoinColumns = @JoinColumn(name = "id_prerequisite")
    )
    private List<Course> prerequisites;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "course_objectives", joinColumns = @JoinColumn(name = "id_course"))
    private List<String> objectives;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "course_content", joinColumns = @JoinColumn(name = "id_course"))
    private List<String> content;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "course_competences", joinColumns = @JoinColumn(name = "id_course"))
    private List<String> competences;

    @ToString.Exclude
    @ManyToMany(mappedBy = "courses", fetch = FetchType.LAZY)
    private List<Career> careers;

    public void addCareer(Career career) {
        if (!careers.contains(career)) {
            careers.add(career);
        }
        if (!career.getCourses().contains(this)) {
            career.getCourses().add(this);
        }
    }

    public void removeCareer(Career career) {
        careers.remove(career);
        career.getCourses().remove(this);
    }
}
