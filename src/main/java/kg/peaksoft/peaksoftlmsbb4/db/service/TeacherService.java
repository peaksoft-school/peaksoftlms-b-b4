package kg.peaksoft.peaksoftlmsbb4.db.service;

import kg.peaksoft.peaksoftlmsbb4.controller.payload.request.TeacherRequest;
import kg.peaksoft.peaksoftlmsbb4.controller.payload.response.CourseResponse;
import kg.peaksoft.peaksoftlmsbb4.controller.payload.response.TeacherPaginationResponse;
import kg.peaksoft.peaksoftlmsbb4.controller.payload.response.TeacherResponse;
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
