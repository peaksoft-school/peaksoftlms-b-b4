package kg.peaksoft.peaksoftlmsbb4.service;

import kg.peaksoft.peaksoftlmsbb4.dto.student.StudentRequest;
import kg.peaksoft.peaksoftlmsbb4.dto.student.StudentResponse;
import kg.peaksoft.peaksoftlmsbb4.enums.StudyFormat;
import kg.peaksoft.peaksoftlmsbb4.model.Student;

import java.util.List;

public interface StudentService {

    StudentResponse saveStudent(StudentRequest studentRequest);

    StudentResponse updateStudent(Long id, StudentRequest studentRequest);

    Student findById(Long id);

    void deleteStudent(Long id);

    List<StudentResponse> findAllStudent();

    List<StudentResponse> findByStudyFormat(StudyFormat studyFormat);

    void assignStudentToCourse(Long course, Long studentId);

}
