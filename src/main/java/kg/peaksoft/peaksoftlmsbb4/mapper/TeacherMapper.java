package kg.peaksoft.peaksoftlmsbb4.mapper;

import kg.peaksoft.peaksoftlmsbb4.converter.Converter;
import kg.peaksoft.peaksoftlmsbb4.dto.TeacherRequest;
import kg.peaksoft.peaksoftlmsbb4.dto.TeacherResponse;
import kg.peaksoft.peaksoftlmsbb4.model.Teacher;
import kg.peaksoft.peaksoftlmsbb4.model.User;
import org.springframework.stereotype.Component;

@Component
public class TeacherMapper implements Converter<Teacher, TeacherRequest, TeacherResponse> {
    @Override
    public Teacher convert(TeacherRequest request) {
        Teacher teacher=new Teacher();
        teacher.setTeacherName(request.getTeacherName());
        teacher.setLastName(request.getLastName());
        teacher.setPhoneNumber(request.getPhoneNumber());
        teacher.setSpecialization(request.getSpecialization());

        User user=new User();
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());

        teacher.setUser(user);
        return teacher;

    }

    @Override
    public TeacherResponse deConvert(Teacher teacher) {
        TeacherResponse teacherResponse=new TeacherResponse();
        teacherResponse.setId(teacher.getId());
        teacherResponse.setTeacherName(teacher.getTeacherName());
        teacherResponse.setLastName(teacher.getLastName());
        teacherResponse.setPhoneNumber(teacher.getPhoneNumber());
        teacherResponse.setSpecialization(teacher.getSpecialization());
        teacherResponse.setEmail(teacher.getUser().getEmail());
        teacherResponse.setPassword(teacher.getUser().getPassword());
        return teacherResponse;

    }
}
