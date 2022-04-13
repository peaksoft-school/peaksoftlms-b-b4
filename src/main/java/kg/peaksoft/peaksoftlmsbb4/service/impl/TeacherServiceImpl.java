package kg.peaksoft.peaksoftlmsbb4.service.impl;

import kg.peaksoft.peaksoftlmsbb4.dto.teacher.TeacherRequest;
import kg.peaksoft.peaksoftlmsbb4.dto.teacher.TeacherResponse;
import kg.peaksoft.peaksoftlmsbb4.exception.BadRequestException;
import kg.peaksoft.peaksoftlmsbb4.exception.NotFoundException;
import kg.peaksoft.peaksoftlmsbb4.mapper.TeacherMapper;
import kg.peaksoft.peaksoftlmsbb4.model.Teacher;
import kg.peaksoft.peaksoftlmsbb4.repository.TeacherRepository;
import kg.peaksoft.peaksoftlmsbb4.repository.UserRepository;
import kg.peaksoft.peaksoftlmsbb4.service.TeacherService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final PasswordEncoder passwordEncoder;
    private final TeacherMapper teacherMapper;
    private final UserRepository userRepository;

    @Override
    public TeacherResponse saveTeacher(TeacherRequest teacherRequest) {

        String email = teacherRequest.getEmail();

        if (teacherRepository.existsByUserEmail((email))) {
            throw new BadRequestException(
                    String.format("teacher with email = %s does not exists", email)
            );
        }
        String encodedPassword = passwordEncoder.encode(teacherRequest.getPassword());
        teacherRequest.setPassword(encodedPassword);

        Teacher teacher = teacherMapper.convert(teacherRequest);

        Teacher teacher1 = teacherRepository.save(teacher);

        return teacherMapper.deConvert(teacher1);

    }

    @Override
    public TeacherResponse updateTeacher(Long id, TeacherRequest teacherRequest) {
        Teacher teacher = findById(id);

        if (!teacher.getTeacherName().equals(teacherRequest.getTeacherName())) {
            teacher.setTeacherName(teacherRequest.getTeacherName());
        }
        if (!teacher.getLastName().equals(teacherRequest.getLastName())) {
            teacher.setLastName(teacherRequest.getLastName());
        }
        if (!teacher.getPhoneNumber().equals(teacherRequest.getPhoneNumber())) {
            teacher.setPhoneNumber(teacherRequest.getPhoneNumber());
        }
        if (!teacher.getSpecialization().equals(teacherRequest.getSpecialization())) {
            teacher.setSpecialization(teacherRequest.getSpecialization());
        }
        if (!teacher.getUser().getEmail().equals(teacherRequest.getEmail())) {
            teacher.getUser().setEmail(teacherRequest.getEmail());
        }
        if (!passwordEncoder.matches(teacherRequest.getPassword(), teacher.getUser().getPassword())) {
            teacher.getUser().setPassword(passwordEncoder.encode(teacherRequest.getPassword()));
        }
        return teacherMapper.deConvert(teacher);

    }

    @Override
    public Teacher findById(Long id) {
        return teacherRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        String.format("teacher with id = %s does not exists", id)
                ));
    }

    @Override
    public void deleteTeacher(Long id) {
        boolean exists =teacherRepository.existsById(id);

        if(!exists){
            throw new BadRequestException(String.format("teacher with id = %s does not exists",id));
        }
        teacherRepository.deleteById(id);
    }

    @Override
    public List<TeacherResponse> findAllTeacher() {
        return teacherRepository.findAll()
                .stream()
                .map(teacherMapper::deConvert)
                .toList();
    }

}