package kg.peaksoft.peaksoftlmsbb4.db.service.impl;

import kg.peaksoft.peaksoftlmsbb4.db.dto.course.CourseResponse;
import kg.peaksoft.peaksoftlmsbb4.db.dto.teacher.TeacherRequest;
import kg.peaksoft.peaksoftlmsbb4.db.dto.teacher.TeacherResponse;
import kg.peaksoft.peaksoftlmsbb4.db.mapper.course.CourseMapper;
import kg.peaksoft.peaksoftlmsbb4.db.mapper.teacher.TeacherMapper;
import kg.peaksoft.peaksoftlmsbb4.db.model.Teacher;
import kg.peaksoft.peaksoftlmsbb4.db.repository.TeacherRepository;
import kg.peaksoft.peaksoftlmsbb4.db.service.TeacherService;
import kg.peaksoft.peaksoftlmsbb4.exceptions.BadRequestException;
import kg.peaksoft.peaksoftlmsbb4.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final PasswordEncoder passwordEncoder;
    private final TeacherMapper teacherMapper;
    private final CourseMapper courseMapper;

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

        log.info("successful save this teacher:{}", teacher1);
        return teacherMapper.deConvert(teacher1);

    }

    @Override
    public TeacherResponse updateTeacher(Long id, TeacherRequest teacherRequest) {
        Teacher teacher = findBy(id);

        if (!teacher.getName().equals(teacherRequest.getTeacherName())) {
            teacher.setName(teacherRequest.getTeacherName());
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
        log.info("successful update teacher this id:{}", id);
        return teacherMapper.deConvert(teacher);

    }

    @Override
    public TeacherResponse findById(Long id) {
        return teacherMapper.deConvert(findBy(id));
    }

    @Override
    public Teacher findBy(Long id) {
        log.info("successful find by id :{}", id);
        return teacherRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        String.format("teacher with id = %s does not exists", id)
                ));
    }

    @Override
    public void deleteTeacher(Long id) {
        boolean exists = teacherRepository.existsById(id);

        if (!exists) {
            throw new BadRequestException(String.format("teacher with id = %s does not exists", id));
        }
        log.info("successful delete this id:{}", id);
        teacherRepository.deleteById(id);
    }

    @Override
    public List<TeacherResponse> findAllTeacher() {
        log.info("successful find all teacher");
        return teacherRepository.findAll()
                .stream()
                .map(teacherMapper::deConvert).collect(Collectors.toList());
    }

    @Override
    public List<CourseResponse> teacherCourses(String email) {
        Teacher teacher = teacherRepository.findTeacherByUserEmail(email);
        return courseMapper.deConvert(teacher.getCourses());
    }


}
