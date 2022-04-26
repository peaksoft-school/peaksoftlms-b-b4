package kg.peaksoft.peaksoftlmsbb4.db.service;

import kg.peaksoft.peaksoftlmsbb4.db.dto.student.StudentRequest;
import kg.peaksoft.peaksoftlmsbb4.db.dto.student.StudentResponse;
import kg.peaksoft.peaksoftlmsbb4.db.enums.StudyFormat;
import kg.peaksoft.peaksoftlmsbb4.db.model.Student;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StudentService {

    StudentResponse saveStudent(StudentRequest studentRequest);

    StudentResponse updateStudent(Long id, StudentRequest studentRequest);

    StudentResponse findById(Long id);

    void deleteStudent(Long id);

    List<StudentResponse> findAllStudent(Pageable pageable);

    List<StudentResponse> findByStudyFormat(StudyFormat studyFormat);

    void assignStudentToCourse(Long course, Long studentId);

    List<Student> findByStudentName(String name);

}
