package kg.peaksoft.peaksoftlmsbb4.db.service;

import kg.peaksoft.peaksoftlmsbb4.controller.payload.response.CoursePaginationResponse;
import kg.peaksoft.peaksoftlmsbb4.controller.payload.request.CourseRequest;
import kg.peaksoft.peaksoftlmsbb4.controller.payload.response.CourseResponse;
import kg.peaksoft.peaksoftlmsbb4.controller.payload.response.StudentResponse;
import kg.peaksoft.peaksoftlmsbb4.controller.payload.request.AssignTeacherRequest;
import kg.peaksoft.peaksoftlmsbb4.controller.payload.response.TeacherResponse;

import java.util.List;

public interface CourseService {

    CourseResponse saveCourse(CourseRequest courseRequest);

    List<CourseResponse> findAll();

    CoursePaginationResponse coursesForPagination(int page, int size);

    CourseResponse findById(Long id);

    CourseResponse update(Long id, CourseRequest courseRequest);

    CourseResponse delete(Long id);

    List<StudentResponse> getAllStudentsByCourseId(Long id);

    List<TeacherResponse> getAllTeacherByCourseId(Long id);

    String assignTeachersToCourse(AssignTeacherRequest assignTeacherRequest);
}
