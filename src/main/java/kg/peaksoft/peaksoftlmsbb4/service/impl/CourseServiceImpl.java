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
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;
    private final StudentMapper studentMapper;
    private final TeacherMapper teacherMapper;

    @Override
    public CourseResponse saveCourse(CourseRequest courseRequest) {
        String name=courseRequest.getCourseName();
        if (courseRepository.existsByCourseName((name))) {
            throw new BadRequestException(
                    String.format("There is such a = %s ", name)
            );
        }
        Course course = courseMapper.convert(courseRequest);
        Course save = courseRepository.save(course);
        return courseMapper.deConvert(save);
    }

    @Override
    public List<CourseResponse> findAll() {
        return courseRepository.findAll().stream().map(courseMapper::deConvert).collect(Collectors.toList());
    }

    @Override
    public Course findById(Long id) {
        return courseRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Not found course with id=%s", id)));
    }

    @Override
    public CourseResponse update(Long id, CourseRequest courseRequest) {
        boolean exists = courseRepository.existsById(id);
        if (!exists) {
            throw new NotFoundException(String.format("Not found with id=%s", id));
        }
        Course course = findById(id);
        courseMapper.update(course, courseRequest);
        return courseMapper.deConvert(course);
    }

    @Override
    public void delete(Long id) {
        boolean existsById = courseRepository.existsById(id);
        if (!existsById) {
            throw new NotFoundException(String.format("Not found course with id=%s", id));
        }
        courseRepository.deleteById(id);
    }

    @Override
    public List<StudentResponse> getAllStudentsByCourseId(Long id) {
        List<StudentResponse> studentResponses = new ArrayList<>();
        List<Student> students = new ArrayList<>();
        List<Group> groupsByCourseId = findById(id).getGroups();
        for (Group g : groupsByCourseId) {
            students.addAll(g.getStudents());
        }
        for (Student s : students) {
            studentResponses.add(studentMapper.deConvert(s));
        }
        return studentResponses;
    }

    @Override
    public List<TeacherResponse> getAllTeacherByCourseId(Long id) {
        List<TeacherResponse> teacherResponses = new ArrayList<>();
        for (Teacher t : findById(id).getTeachers()) {
            teacherResponses.add(teacherMapper.deConvert(t));
        }
        return teacherResponses;
    }


}