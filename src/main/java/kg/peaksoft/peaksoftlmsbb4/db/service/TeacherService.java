package kg.peaksoft.peaksoftlmsbb4.db.service;

import kg.peaksoft.peaksoftlmsbb4.controller.payload.course.CourseResponse;
import kg.peaksoft.peaksoftlmsbb4.controller.payload.teacher.TeacherPaginationResponse;
import kg.peaksoft.peaksoftlmsbb4.controller.payload.teacher.TeacherRequest;
import kg.peaksoft.peaksoftlmsbb4.controller.payload.teacher.TeacherResponse;
import kg.peaksoft.peaksoftlmsbb4.db.model.Teacher;

import java.util.Deque;

public interface TeacherService {

    TeacherResponse saveTeacher(TeacherRequest teacherRequest);

    TeacherResponse updateTeacher(Long id, TeacherRequest teacherRequest);

    TeacherResponse findById(Long id);

    Teacher findBy(Long id);

    TeacherResponse deleteTeacher(Long id);

    TeacherPaginationResponse findAllTeacher(int page, int size);

    Deque<CourseResponse> teacherCourses(String email);


    Deque<TeacherResponse> teacherResponsesForAssign(Long id);
}
