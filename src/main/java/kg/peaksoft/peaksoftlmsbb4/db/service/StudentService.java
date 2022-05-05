package kg.peaksoft.peaksoftlmsbb4.db.service;

import kg.peaksoft.peaksoftlmsbb4.db.dto.course.CourseResponse;
import kg.peaksoft.peaksoftlmsbb4.db.dto.result.ResultResponse;
import kg.peaksoft.peaksoftlmsbb4.db.dto.student.AssignStudentRequest;
import kg.peaksoft.peaksoftlmsbb4.db.dto.student.StudentPaginationResponse;
import kg.peaksoft.peaksoftlmsbb4.db.dto.student.StudentRequest;
import kg.peaksoft.peaksoftlmsbb4.db.dto.student.StudentResponse;
import kg.peaksoft.peaksoftlmsbb4.db.enums.StudyFormat;
import kg.peaksoft.peaksoftlmsbb4.db.model.Student;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface StudentService {

    StudentResponse saveStudent(StudentRequest studentRequest);

    StudentResponse updateStudent(Long id, StudentRequest studentRequest);

    StudentResponse findById(Long id);

    String deleteStudent(Long id);

    List<StudentResponse> findAllStudent();

    StudentPaginationResponse getAll(int size,int page);

    List<StudentResponse> findByStudyFormat(StudyFormat studyFormat);

    String assignStudentToCourse(AssignStudentRequest assignStudentRequest);

    List<Student> findByStudentName(String name);

    List<StudentResponse> importExcelFile(MultipartFile files, Long id) throws IOException;

    List<CourseResponse> studentCourses(String email);

    List<ResultResponse> studentResult(String email);
}
