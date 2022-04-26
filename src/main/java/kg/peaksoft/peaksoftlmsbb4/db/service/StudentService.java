package kg.peaksoft.peaksoftlmsbb4.db.service;

import kg.peaksoft.peaksoftlmsbb4.db.dto.student.AssignStudentRequest;
import kg.peaksoft.peaksoftlmsbb4.db.dto.student.StudentRequest;
import kg.peaksoft.peaksoftlmsbb4.db.dto.student.StudentResponse;
import kg.peaksoft.peaksoftlmsbb4.db.enums.StudyFormat;
import kg.peaksoft.peaksoftlmsbb4.db.model.Student;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface StudentService {

    StudentResponse saveStudent(StudentRequest studentRequest);

    StudentResponse updateStudent(Long id, StudentRequest studentRequest);

    StudentResponse findById(Long id);

    void deleteStudent(Long id);

    List<StudentResponse> findAllStudent(Pageable pageable);

    List<StudentResponse> findByStudyFormat(StudyFormat studyFormat);

    void assignStudentToCourse(AssignStudentRequest assignStudentRequest, Long studentId);

    List<Student> findByStudentName(String name);

    List<StudentResponse> importExcelFile(MultipartFile files, Long id) throws IOException;

}
