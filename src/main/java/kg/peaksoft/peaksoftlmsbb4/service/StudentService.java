package kg.peaksoft.peaksoftlmsbb4.service;

import kg.peaksoft.peaksoftlmsbb4.dto.student.StudentRequest;
import kg.peaksoft.peaksoftlmsbb4.dto.student.StudentResponse;
import kg.peaksoft.peaksoftlmsbb4.enums.StudyFormat;
import kg.peaksoft.peaksoftlmsbb4.model.Student;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StudentService {

    StudentResponse saveStudent(StudentRequest studentRequest);

    StudentResponse updateStudent(Long id, StudentRequest studentRequest);

    StudentResponse getById(Long id);

    void deleteStudent(Long id);

    List<StudentResponse> findAllStudent(Pageable pageable);

    List<StudentResponse> findByStudyFormat(StudyFormat studyFormat);

    void assignStudentToCourse(Long course, Long studentId);

}
