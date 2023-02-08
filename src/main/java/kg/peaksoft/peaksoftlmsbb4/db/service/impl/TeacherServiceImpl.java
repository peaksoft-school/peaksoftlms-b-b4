package kg.peaksoft.peaksoftlmsbb4.db.service.impl;

import kg.peaksoft.peaksoftlmsbb4.controller.payload.request.TeacherRequest;
import kg.peaksoft.peaksoftlmsbb4.controller.payload.response.CourseResponse;
import kg.peaksoft.peaksoftlmsbb4.controller.payload.response.TeacherPaginationResponse;
import kg.peaksoft.peaksoftlmsbb4.controller.payload.response.TeacherResponse;
import kg.peaksoft.peaksoftlmsbb4.db.mapper.CourseMapper;
import kg.peaksoft.peaksoftlmsbb4.db.mapper.TeacherMapper;
import kg.peaksoft.peaksoftlmsbb4.db.model.Course;
import kg.peaksoft.peaksoftlmsbb4.db.model.Teacher;
import kg.peaksoft.peaksoftlmsbb4.db.repository.CourseRepository;
import kg.peaksoft.peaksoftlmsbb4.db.repository.TeacherRepository;
import kg.peaksoft.peaksoftlmsbb4.db.repository.UserRepository;
import kg.peaksoft.peaksoftlmsbb4.db.service.TeacherService;
import kg.peaksoft.peaksoftlmsbb4.exceptions.BadRequestException;
import kg.peaksoft.peaksoftlmsbb4.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

@Slf4j
@Transactional
@AllArgsConstructor
@Service
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final PasswordEncoder passwordEncoder;
    private final TeacherMapper teacherMapper;
    private final CourseMapper courseMapper;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;

    @Override
    public TeacherResponse saveTeacher(TeacherRequest request) {
        String email = request.getEmail();
        if (userRepository.existsByEmail((email))) {
            log.error("user with this email already exists:{}", email);
            throw new BadRequestException(String.format("user with this email = %s already exists:", email));
        }
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        request.setPassword(encodedPassword);
        Teacher teacher = teacherMapper.convert(request);
        Teacher teacher1 = teacherRepository.save(teacher);
        log.info("successful save  teacher:{}", teacher1);
        return teacherMapper.deConvert(teacher1);
    }

    @Override
    public TeacherResponse updateTeacher(Long id, TeacherRequest request) {
        boolean exists = teacherRepository.existsById(id);
        if (!exists) {
            log.error("not found teacher with id:{}", id);
            throw new NotFoundException(String.format("Not found teacher with id=%s", id));
        }
        Teacher teacher = findBy(id);
        if (!teacher.getName().equals(request.getTeacherName())) {
            teacher.setName(request.getTeacherName());
        }
        if (!teacher.getLastName().equals(request.getLastName())) {
            teacher.setLastName(request.getLastName());
        }
        if (!teacher.getPhoneNumber().equals(request.getPhoneNumber())) {
            teacher.setPhoneNumber(request.getPhoneNumber());
        }
        if (!teacher.getSpecialization().equals(request.getSpecialization())) {
            teacher.setSpecialization(request.getSpecialization());
        }
        if (!teacher.getUser().getEmail().equals(request.getEmail())) {
            teacher.getUser().setEmail(request.getEmail());
        }
        if (!passwordEncoder.matches(request.getPassword(), teacher.getUser().getPassword())) {
            teacher.getUser().setPassword(passwordEncoder.encode(request.getPassword()));
        }
        log.info("successful update teacher by id:{}", id);
        return teacherMapper.deConvert(teacher);
    }

    @Override
    public TeacherResponse findById(Long id) {
        log.info("successfully find teacher by id:{}", id);
        return teacherMapper.deConvert(findBy(id));
    }

    @Override
    public Teacher findBy(Long id) {
        log.info("successful find teacher by id :{}", id);
        return teacherRepository.findById(id).orElseThrow(() ->
                new NotFoundException(String.format("teacher with id = %s does not exists", id)));
    }

    @Override
    public TeacherResponse deleteTeacher(Long id) {
        boolean exists = teacherRepository.existsById(id);
        if (!exists) {
            log.error("not found teacher with id:{}", id);
            throw new BadRequestException(String.format("teacher with id = %s does not exists", id));
        }
        Teacher teacher = teacherRepository.findById(id).orElseThrow(() ->
                new NotFoundException(String.format("teacher not found %s with id", id)));
        log.info("successful delete teacher by id:{}", id);
        teacherRepository.deleteById(id);
        return teacherMapper.deConvert(teacher);
    }

    @Override
    public TeacherPaginationResponse findAllTeacher(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        TeacherPaginationResponse teacherPaginationResponse = new TeacherPaginationResponse();
        teacherPaginationResponse.setTeachers(teacherMapper.deConvert(teacherRepository.findAll(pageable).getContent()));
        teacherPaginationResponse.setPages(teacherRepository.findAll(pageable).getTotalPages());
        teacherPaginationResponse.setCurrentPage(pageable.getPageNumber());
        log.info("successful find all teachers");
        return teacherPaginationResponse;
    }

    @Override
    public Deque<CourseResponse> teacherCourses(String email) {
        Teacher teacher = teacherRepository.findTeacherByUserEmail(email);
        log.info("find teacher by user email:{}", email);
        return courseMapper.deConvert(teacher.getCourses());
    }

    @Override
    public Deque<TeacherResponse> teacherResponsesForAssign(Long id) {
        List<Teacher> all = teacherRepository.findAll();
        Course course = courseRepository.findById(id).orElseThrow(() ->
                new NotFoundException("here have mistake id course not found!!! "));
        all.removeAll(course.getTeachers());
        return new ArrayDeque<>(teacherMapper.deConvert(all));
    }

}
