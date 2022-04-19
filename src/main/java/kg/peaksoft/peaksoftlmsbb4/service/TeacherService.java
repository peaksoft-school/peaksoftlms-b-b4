package kg.peaksoft.peaksoftlmsbb4.service;

import kg.peaksoft.peaksoftlmsbb4.dto.course.CourseResponse;
import kg.peaksoft.peaksoftlmsbb4.dto.teacher.TeacherRequest;
import kg.peaksoft.peaksoftlmsbb4.dto.teacher.TeacherResponse;
import kg.peaksoft.peaksoftlmsbb4.model.Teacher;

import java.util.List;

public interface TeacherService {

    TeacherResponse saveTeacher(TeacherRequest teacherRequest);

    TeacherResponse updateTeacher(Long id, TeacherRequest teacherRequest);

    TeacherResponse findById(Long id);

    void deleteTeacher(Long id);

    List<TeacherResponse> findAllTeacher();

    List<CourseResponse> teacherCourses(Long id);

    void assignTeacherToCourse(Long course, Long teacherId);

}
