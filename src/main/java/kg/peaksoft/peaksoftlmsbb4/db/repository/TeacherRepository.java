package kg.peaksoft.peaksoftlmsbb4.db.repository;

import kg.peaksoft.peaksoftlmsbb4.db.model.Teacher;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    Teacher findTeacherByUserEmail(String email);

    @Query("select t from Teacher t WHERE t in (select c.teachers from Course c where c.id = :id)")
    List<Teacher> findAllTeacherForCourseById(Long id);
}
