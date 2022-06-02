package kg.peaksoft.peaksoftlmsbb4.db.repository;

import kg.peaksoft.peaksoftlmsbb4.db.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    boolean existsByCourseName(String name);

}
