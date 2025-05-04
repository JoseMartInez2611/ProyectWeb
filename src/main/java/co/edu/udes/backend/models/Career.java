package co.edu.udes.backend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "careers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Career {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name",
            nullable = false,
            columnDefinition = "VARCHAR(255)"
    )
    private String name;

    @Column(name = "description",
            nullable = false,
            columnDefinition = "VARCHAR(1024)"
    )
    private String description;

    @Column(name = "code",
            nullable = false,
            columnDefinition = "VARCHAR(6)"
    )
    private String code;

    @Column(name = "duration",
            nullable = false,
            columnDefinition = "INTEGER"
    )
    private int duration;

    @Column(name = "modality",
            nullable = false,
            columnDefinition = "VARCHAR(255)"
    )
    private String modality;

    @Column(name = "type",
            nullable = false,
            columnDefinition = "VARCHAR(255)"
    )
    private String type;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "career")
    private List<Course> courses;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "careers")
    private List<Teacher> teachers;

    public void addCourse(Course course) {
        if (!courses.contains(course)) {
            courses.add(course);
        }
    }

    public void removeCourse(Course course) {
        courses.remove(course);
    }
}
