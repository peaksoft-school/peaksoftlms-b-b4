package kg.peaksoft.peaksoftlmsbb4.db.service;

import kg.peaksoft.peaksoftlmsbb4.db.dto.course.CoursePaginationResponse;
import kg.peaksoft.peaksoftlmsbb4.db.dto.course.CourseRequest;
import kg.peaksoft.peaksoftlmsbb4.db.dto.course.CourseResponse;
import kg.peaksoft.peaksoftlmsbb4.db.dto.student.StudentResponse;
import kg.peaksoft.peaksoftlmsbb4.db.dto.teacher.AssignTeacherRequest;
import kg.peaksoft.peaksoftlmsbb4.db.dto.teacher.TeacherResponse;

import java.util.List;

public interface CourseService {

    CourseResponse saveCourse(CourseRequest courseRequest);

    List<CourseResponse> findAll();
    CoursePaginationResponse coursesForPagination(int page,int size);

    CourseResponse findById(Long id);

    CourseResponse update(Long id, CourseRequest courseRequest);

    void delete(Long id);

    List<StudentResponse> getAllStudentsByCourseId(Long id);

    List<TeacherResponse> getAllTeacherByCourseId(Long id);

    void assignTeachersToCourse(AssignTeacherRequest assignTeacherRequest);
}
