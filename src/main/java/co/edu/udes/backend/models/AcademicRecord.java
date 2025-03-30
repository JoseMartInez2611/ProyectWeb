package co.edu.udes.backend.models;

import java.util.HashMap;
import java.util.Map;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "academic_records")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AcademicRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "academic_average")
    private float academicAverage;

    @OneToOne
    @JoinColumn(name = "student_id", unique = true, nullable = false)
    private Student student;

    @ElementCollection
    @CollectionTable(name = "academic_record_group",
            joinColumns = @JoinColumn(name = "academic_record_id")
    )
    @MapKeyJoinColumn(name = "group_id")
    @Column(name = "grade")
    private Map<Group, Float> notes = new HashMap<>();

    public void checkAcademicHistory() {

    }

    public void consultAssistance() {

    }

    public int accumulateCredits() {
        return notes.keySet().stream().
                mapToInt(group -> group.getCourse().getCredits())
                .sum();
    }

    public float calculateAverage() {
        float totalAccumulatedNotes = notes.entrySet().stream()
                .map(entry ->
                        entry.getValue() * entry.getKey().getCourse().getCredits())
                .reduce(0f, Float::sum);
        return totalAccumulatedNotes / accumulateCredits();
    }
}
