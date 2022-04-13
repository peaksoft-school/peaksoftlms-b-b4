package kg.peaksoft.peaksoftlmsbb4.repository;

import kg.peaksoft.peaksoftlmsbb4.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher,Long> {


//    boolean existsByEmail(String email);

     @Query("select case when count(c) > 0 then true else false end" +
     " from Teacher c where c.user.email = ?1")
    boolean existsByUserEmail(String email);
}
