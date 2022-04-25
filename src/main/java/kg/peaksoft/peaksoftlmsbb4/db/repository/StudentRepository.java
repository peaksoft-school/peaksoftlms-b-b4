package kg.peaksoft.peaksoftlmsbb4.db.repository;

import kg.peaksoft.peaksoftlmsbb4.db.enums.StudyFormat;
import kg.peaksoft.peaksoftlmsbb4.db.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>, PagingAndSortingRepository<Student,Long> {
    List<Student> findByStudyFormat(StudyFormat studyFormat);

    boolean existsByEmail(String email);
}
