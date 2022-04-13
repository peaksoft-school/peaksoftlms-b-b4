package kg.peaksoft.peaksoftlmsbb4.mapper;

import kg.peaksoft.peaksoftlmsbb4.converter.Converter;
import kg.peaksoft.peaksoftlmsbb4.dto.teacher.TeacherRequest;
import kg.peaksoft.peaksoftlmsbb4.dto.teacher.TeacherResponse;
import kg.peaksoft.peaksoftlmsbb4.enums.Role;
import kg.peaksoft.peaksoftlmsbb4.exception.BadRequestException;
import kg.peaksoft.peaksoftlmsbb4.model.Teacher;
import kg.peaksoft.peaksoftlmsbb4.model.User;
import kg.peaksoft.peaksoftlmsbb4.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class TeacherMapper implements Converter<Teacher, TeacherRequest, TeacherResponse> {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Override
    public Teacher convert(TeacherRequest request) {
        Teacher teacher = new Teacher();
        teacher.setTeacherName(request.getTeacherName());
        teacher.setLastName(request.getLastName());
        teacher.setPhoneNumber(request.getPhoneNumber());
        teacher.setSpecialization(request.getSpecialization());
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.TEACHER);



        teacher.setUser(user);

        return teacher;

    }

    @Override
    public TeacherResponse deConvert(Teacher teacher) {
        TeacherResponse teacherResponse = new TeacherResponse();
        teacherResponse.setId(teacher.getId());
        teacherResponse.setTeacherName(teacher.getTeacherName());
        teacherResponse.setLastName(teacher.getLastName());
        teacherResponse.setPhoneNumber(teacher.getPhoneNumber());
        teacherResponse.setSpecialization(teacher.getSpecialization());
        teacherResponse.setEmail(teacher.getUser().getEmail());
        teacherResponse.setPassword(teacher.getUser().getPassword());
        teacherResponse.setRole(teacher.getUser().getRole());
        return teacherResponse;

    }
}
