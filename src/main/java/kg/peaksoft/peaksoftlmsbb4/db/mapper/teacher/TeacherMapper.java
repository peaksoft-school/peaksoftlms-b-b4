package kg.peaksoft.peaksoftlmsbb4.db.mapper.teacher;

import kg.peaksoft.peaksoftlmsbb4.controller.payload.converter.Converter;
import kg.peaksoft.peaksoftlmsbb4.controller.payload.teacher.TeacherRequest;
import kg.peaksoft.peaksoftlmsbb4.controller.payload.teacher.TeacherResponse;
import kg.peaksoft.peaksoftlmsbb4.db.enums.Role;
import kg.peaksoft.peaksoftlmsbb4.db.model.Teacher;
import kg.peaksoft.peaksoftlmsbb4.db.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

@Component
@AllArgsConstructor
public class TeacherMapper implements Converter<Teacher, TeacherRequest, TeacherResponse> {
    @Override
    public Teacher convert(TeacherRequest request) {
        Teacher teacher = new Teacher();
        teacher.setName(request.getTeacherName());
        teacher.setLastName(request.getLastName());
        teacher.setPhoneNumber(request.getPhoneNumber());
        teacher.setSpecialization(request.getSpecialization());
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setRole(Role.INSTRUCTOR);
        teacher.setUser(user);
        return teacher;

    }

    @Override
    public TeacherResponse deConvert(Teacher teacher) {
        TeacherResponse teacherResponse = new TeacherResponse();
        teacherResponse.setId(teacher.getId());
        teacherResponse.setTeacherName(teacher.getName());
        teacherResponse.setLastName(teacher.getLastName());
        teacherResponse.setPhoneNumber(teacher.getPhoneNumber());
        teacherResponse.setSpecialization(teacher.getSpecialization());
        teacherResponse.setEmail(teacher.getUser().getEmail());
        teacherResponse.setFullName(teacher.getName() + " " + teacher.getLastName());
        return teacherResponse;

    }

    public Deque<TeacherResponse> deConvert(List<Teacher> teachers) {
        Deque<TeacherResponse> teacherResponses = new ArrayDeque<>();
        for (Teacher t : teachers) {
            teacherResponses.addFirst(deConvert(t));
        }
        return teacherResponses;
    }
}
