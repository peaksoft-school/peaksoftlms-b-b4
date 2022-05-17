package kg.peaksoft.peaksoftlmsbb4.db.service.impl;

import kg.peaksoft.peaksoftlmsbb4.db.dto.course.CoursePaginationResponse;
import kg.peaksoft.peaksoftlmsbb4.db.dto.course.CourseRequest;
import kg.peaksoft.peaksoftlmsbb4.db.dto.course.CourseResponse;
import kg.peaksoft.peaksoftlmsbb4.db.dto.student.StudentResponse;
import kg.peaksoft.peaksoftlmsbb4.db.dto.teacher.AssignTeacherRequest;
import kg.peaksoft.peaksoftlmsbb4.db.dto.teacher.TeacherResponse;
import kg.peaksoft.peaksoftlmsbb4.db.mapper.course.CourseMapper;
import kg.peaksoft.peaksoftlmsbb4.db.mapper.student.StudentMapper;
import kg.peaksoft.peaksoftlmsbb4.db.mapper.teacher.TeacherMapper;
import kg.peaksoft.peaksoftlmsbb4.db.model.Course;
import kg.peaksoft.peaksoftlmsbb4.db.model.Student;
import kg.peaksoft.peaksoftlmsbb4.db.model.Teacher;
import kg.peaksoft.peaksoftlmsbb4.db.repository.CourseRepository;
import kg.peaksoft.peaksoftlmsbb4.db.service.CourseService;
import kg.peaksoft.peaksoftlmsbb4.db.service.TeacherService;
import kg.peaksoft.peaksoftlmsbb4.exceptions.BadRequestException;
import kg.peaksoft.peaksoftlmsbb4.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
    private final AWSS3Service awss3Service;

    @Override
    public CourseResponse saveCourse(CourseRequest courseRequest) {
        String name = courseRequest.getCourseName();
        if (courseRepository.existsByCourseName((name))) {
            log.error("there is such a course name:{}", name);
            throw new BadRequestException(
                    String.format("There is such a course name = %s ", name)
            );
        }
        Course save = courseRepository.save(courseMapper.convert(courseRequest));
        log.info("successful save this course:{}", save);
        return courseMapper.deConvert(save);
    }

    @Override
    public List<CourseResponse> findAll() {
        log.info("successful find all courses");
        return courseRepository.findAll().stream().map(
                courseMapper::deConvert).collect(Collectors.toList());
    }

    @Override
    public CoursePaginationResponse coursesForPagination(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        CoursePaginationResponse coursePaginationResponse = new CoursePaginationResponse();
        coursePaginationResponse.setCourses(courseMapper.deConvert(courseRepository.findAll(pageable).getContent()));
        coursePaginationResponse.setPages(courseRepository.findAll(pageable).getTotalPages());
        coursePaginationResponse.setCurrentPage(pageable.getPageNumber());
        return coursePaginationResponse;
    }

    @Override
    public CourseResponse findById(Long id) {
        log.info("successful get by id course :{}", id);
        return courseMapper.deConvert(getById(id));
    }

    @Override
    public CourseResponse update(Long id, CourseRequest courseRequest) {
        boolean exists = courseRepository.existsById(id);
        if (!exists) {
            log.error("not found course with id:{}", id);
            throw new NotFoundException(String.format("Course with id=%s not found ", id));
        }
        Course course = getById(id);
        courseMapper.update(course, courseRequest);
        log.info("successful update this course:{}", course);
        return courseMapper.deConvert(course);
    }

    @Override
    public Long delete(Long id) {
        boolean existsById = courseRepository.existsById(id);
        if (!existsById) {
            log.error("not found course with id:{}", id);
            throw new NotFoundException(String.format(" course with id=%s does not exists", id));
        }
        log.info("successful delete course with id:{}", id);
        if (!courseRepository.getById(id).getImage().equals(" ")) {
            awss3Service.deleteFile(courseRepository.getById(id).getImage());
        }
        courseRepository.deleteById(id);
        log.info("successful delete by this id:{}", id);
        return id;
    }

    @Override
    public List<StudentResponse> getAllStudentsByCourseId(Long id) {
        List<StudentResponse> studentResponses = new ArrayList<>();
        for (Student s : getById(id).getStudents()) {
            studentResponses.add(studentMapper.deConvert(s));
        }
        log.info("successful getAll students by Course Id:{}", id);
        return studentResponses;
    }

    @Override
    public List<TeacherResponse> getAllTeacherByCourseId(Long id) {
        List<TeacherResponse> teacherResponses = new ArrayList<>();
        for (Teacher t : getById(id).getTeachers()) {
            teacherResponses.add(teacherMapper.deConvert(t));
        }
        log.info("successful getAll teachers by Course Id:{}", id);
        return teacherResponses;
    }

    @Transactional
    @Override
    public String assignTeachersToCourse(AssignTeacherRequest assignTeacherRequest) {
        Course course = courseRepository.findById(assignTeacherRequest.getCourseId())
                .orElseThrow(() ->
                        new NotFoundException(String.format("Course with id = %s not found", assignTeacherRequest.getCourseId())));

        for (Long id : assignTeacherRequest.getTeacherId()) {
            course.addTeacher(teacherService.findBy(id));
        }
        courseRepository.save(course);
        log.info("successful assign teacher with id=%s to course");
        return String.format("Teachers added to %s course", course.getCourseName());
    }

    private Course getById(Long id) {
        return courseRepository.findById(id).orElseThrow(() -> new NotFoundException(
                String.format("Course with id=%s does not exists", id)
        ));
    }
}