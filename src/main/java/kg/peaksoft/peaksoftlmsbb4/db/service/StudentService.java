package kg.peaksoft.peaksoftlmsbb4.db.service;

import kg.peaksoft.peaksoftlmsbb4.db.dto.student.AssignStudentRequest;
import kg.peaksoft.peaksoftlmsbb4.db.dto.student.StudentPaginationResponse;
import kg.peaksoft.peaksoftlmsbb4.db.dto.student.StudentRequest;
import kg.peaksoft.peaksoftlmsbb4.db.dto.student.StudentResponse;
import kg.peaksoft.peaksoftlmsbb4.db.enums.StudyFormat;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface StudentService {

    StudentResponse saveStudent(StudentRequest studentRequest);

    StudentResponse updateStudent(Long id, StudentRequest studentRequest);

    StudentResponse findById(Long id);

    StudentResponse deleteStudent(Long id);

    StudentPaginationResponse getAll(int size, int page, StudyFormat studyFormat);

    String assignStudentToCourse(AssignStudentRequest assignStudentRequest);

    List<StudentResponse> findByStudentName(String name);

    List<StudentResponse> importExcelFile(MultipartFile files, Long id) throws Exception;

}
