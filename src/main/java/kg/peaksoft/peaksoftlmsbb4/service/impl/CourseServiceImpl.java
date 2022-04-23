package kg.peaksoft.peaksoftlmsbb4.service.impl;

import kg.peaksoft.peaksoftlmsbb4.dto.course.CourseRequest;
import kg.peaksoft.peaksoftlmsbb4.dto.course.CourseResponse;
import kg.peaksoft.peaksoftlmsbb4.dto.student.StudentResponse;
import kg.peaksoft.peaksoftlmsbb4.dto.teacher.TeacherResponse;
import kg.peaksoft.peaksoftlmsbb4.exception.BadRequestException;
import kg.peaksoft.peaksoftlmsbb4.exception.NotFoundException;
import kg.peaksoft.peaksoftlmsbb4.mapper.course.CourseMapper;
import kg.peaksoft.peaksoftlmsbb4.mapper.student.StudentMapper;
import kg.peaksoft.peaksoftlmsbb4.mapper.teacher.TeacherMapper;
import kg.peaksoft.peaksoftlmsbb4.model.Course;
import kg.peaksoft.peaksoftlmsbb4.model.Group;
import kg.peaksoft.peaksoftlmsbb4.model.Student;
import kg.peaksoft.peaksoftlmsbb4.model.Teacher;
import kg.peaksoft.peaksoftlmsbb4.repository.CourseRepository;
import kg.peaksoft.peaksoftlmsbb4.service.CourseService;
import kg.peaksoft.peaksoftlmsbb4.service.TeacherService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;
    private final StudentMapper studentMapper;
    private final TeacherMapper teacherMapper;
    private final TeacherService teacherService;

    @Override
    public CourseResponse saveCourse(CourseRequest courseRequest) {
        String name = courseRequest.getCourseName();
        if (courseRepository.existsByCourseName((name))) {
            throw new BadRequestException(
                    String.format("There is such a = %s ", name)
            );
        }
        Course save = courseRepository.save(courseMapper.convert(courseRequest));
        log.info("successful save this course:{}", save);
        return courseMapper.deConvert(save);
    }

    @Override
    public List<CourseResponse> findAll() {
        log.info("successful find all");
        return courseRepository.findAll().stream().map(
                courseMapper::deConvert).collect(Collectors.toList());
    }

    @Override
    public CourseResponse findById(Long id) {
        log.info("successful find by this id:{}", id);
        return courseMapper.deConvert(getById(id));
    }

    @Override
    public CourseResponse update(Long id, CourseRequest courseRequest) {
        boolean exists = courseRepository.existsById(id);
        if (!exists) {
            throw new NotFoundException(String.format("Not found with id=%s", id));
        }
        Course course = getById(id);
        courseMapper.update(course, courseRequest);
        log.info("successful update this course:{}", course);
        return courseMapper.deConvert(course);
    }

    @Override
    public void delete(Long id) {
        boolean existsById = courseRepository.existsById(id);
        if (!existsById) {
            throw new NotFoundException(String.format("Not found course with id=%s", id));
        }
        log.info("successful delete by this id:{}", id);
        courseRepository.deleteById(id);
    }

    @Override
    public List<StudentResponse> getAllStudentsByCourseId(Long id) {
        List<StudentResponse> studentResponses = new ArrayList<>();
        for (Student s : getById(id).getStudents()) {
            studentResponses.add(studentMapper.deConvert(s));
        }
        log.info("successful getAll Students by Course Id");
        return studentResponses;
    }

    @Override
    public List<TeacherResponse> getAllTeacherByCourseId(Long id) {
        List<TeacherResponse> teacherResponses = new ArrayList<>();
        for (Teacher t : getById(id).getTeachers()) {
            teacherResponses.add(teacherMapper.deConvert(t));
        }
        log.info("successful getAll teacher by Course Id");
        return teacherResponses;
    }

    @Override
    public Course getById(Long id) {
        return courseRepository.getById(id);
    }

    @Override
    public void assignTeachersToCourse(Long courseId, List<Long> teacherId) {
        Course course1 = getById(courseId);
        for (Long id:teacherId) {
            course1.addTeacher(teacherService.findBy(id));
        }
        log.info("successful assign teacher with id=%s to course");
    }


}