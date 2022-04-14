package kg.peaksoft.peaksoftlmsbb4.repository;

import kg.peaksoft.peaksoftlmsbb4.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    boolean existsByUserEmail(String email);
}
