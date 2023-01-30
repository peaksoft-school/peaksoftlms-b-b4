package kg.peaksoft.peaksoftlmsbb4.db.service;

import kg.peaksoft.peaksoftlmsbb4.controller.payload.response.CourseResponse;
import kg.peaksoft.peaksoftlmsbb4.controller.payload.request.AssignStudentRequest;
import kg.peaksoft.peaksoftlmsbb4.controller.payload.response.StudentPaginationResponse;
import kg.peaksoft.peaksoftlmsbb4.controller.payload.request.StudentRequest;
import kg.peaksoft.peaksoftlmsbb4.controller.payload.response.StudentResponse;
import kg.peaksoft.peaksoftlmsbb4.db.enums.StudyFormat;
import org.springframework.web.multipart.MultipartFile;

import java.util.Deque;
import java.util.List;

public interface StudentService {

    StudentResponse saveStudent(StudentRequest studentRequest);

    StudentResponse updateStudent(Long id, StudentRequest studentRequest);

    StudentResponse findById(Long id);

    StudentResponse deleteStudent(Long id);

    StudentPaginationResponse getAll(int size, int page, StudyFormat studyFormat);

    StudentResponse assignStudentToCourse(AssignStudentRequest assignStudentRequest);

    Deque<StudentResponse> findByStudentName(String name);

    List<StudentResponse> importExcelFile(MultipartFile files, Long id) throws Exception;

    Deque<CourseResponse> getStudentCourses(String email);
}
