package kg.peaksoft.peaksoftlmsbb4.service.impl;

import kg.peaksoft.peaksoftlmsbb4.dto.TeacherRequest;
import kg.peaksoft.peaksoftlmsbb4.dto.TeacherResponse;
import kg.peaksoft.peaksoftlmsbb4.exception.BadRequestException;
import kg.peaksoft.peaksoftlmsbb4.mapper.TeacherMapper;
import kg.peaksoft.peaksoftlmsbb4.model.Teacher;
import kg.peaksoft.peaksoftlmsbb4.repository.TeacherRepository;
import kg.peaksoft.peaksoftlmsbb4.service.TeacherService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.Map;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final TeacherMapper teacherMapper;

    @Override
    public TeacherResponse register(TeacherRequest request) {
        String email = request.getEmail();
        if (teacherRepository.existsByUserEmail(email)) {
            throw new BadRequestException(String.format("teacher with email = %s already in use  ", email));

        }
        String encodedPassword = passwordEncoder.encode(request.getPassword());

        Teacher teacher1 = teacherMapper.convert(request);

        Teacher teacher = teacherRepository.save(teacher1);

        request.setPassword(encodedPassword);

        return teacherMapper.deConvert(teacher);


    }

    @Override
    public void  deleteById(Long teacherId) {
        boolean teacherId1 = teacherRepository.existsById(teacherId);

        if (!teacherId1) {
            throw new BadRequestException(
                    String.format("client with id = %s does not exists", teacherId)
            );

        }
        teacherRepository.deleteById(teacherId);
    }

    @Override
    public TeacherResponse updateTeacher(Long id, TeacherRequest teacherRequest) {
        return null;
    }


}
