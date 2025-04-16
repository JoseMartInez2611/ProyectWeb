package co.edu.udes.backend.repositories;

import co.edu.udes.backend.models.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {
    List<Lesson> findByGroupId(Long groupId);
    List<Lesson> findByGroupIdIn(List<Long> groupIds);
}
