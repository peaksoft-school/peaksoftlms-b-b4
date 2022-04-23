package kg.peaksoft.peaksoftlmsbb4.repository;

import kg.peaksoft.peaksoftlmsbb4.db.model.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonRepository extends JpaRepository<Lesson,Long> {
}
