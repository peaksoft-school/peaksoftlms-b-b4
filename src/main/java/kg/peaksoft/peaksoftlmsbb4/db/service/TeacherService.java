package kg.peaksoft.peaksoftlmsbb4.db.service;

import kg.peaksoft.peaksoftlmsbb4.db.dto.course.CourseResponse;
import kg.peaksoft.peaksoftlmsbb4.db.dto.teacher.TeacherRequest;
import kg.peaksoft.peaksoftlmsbb4.db.dto.teacher.TeacherResponse;
import kg.peaksoft.peaksoftlmsbb4.db.model.Teacher;

import java.util.Deque;
import java.util.List;

public interface TeacherService {

    TeacherResponse saveTeacher(TeacherRequest teacherRequest);

    TeacherResponse updateTeacher(Long id, TeacherRequest teacherRequest);

    TeacherResponse findById(Long id);

    Teacher findBy(Long id);

    TeacherResponse deleteTeacher(Long id);

    List<TeacherResponse> findAllTeacher();

    Deque<CourseResponse> teacherCourses(String email);


}
