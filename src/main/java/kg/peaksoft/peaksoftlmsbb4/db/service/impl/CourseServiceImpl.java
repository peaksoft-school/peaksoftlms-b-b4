package kg.peaksoft.peaksoftlmsbb4.db.service.impl;

import com.amazonaws.services.kms.model.AlreadyExistsException;
import kg.peaksoft.peaksoftlmsbb4.controller.payload.response.CoursePaginationResponse;
import kg.peaksoft.peaksoftlmsbb4.controller.payload.request.CourseRequest;
import kg.peaksoft.peaksoftlmsbb4.controller.payload.response.CourseResponse;
import kg.peaksoft.peaksoftlmsbb4.controller.payload.response.StudentResponse;
import kg.peaksoft.peaksoftlmsbb4.controller.payload.request.AssignTeacherRequest;
import kg.peaksoft.peaksoftlmsbb4.controller.payload.response.TeacherResponse;
import kg.peaksoft.peaksoftlmsbb4.db.mapper.course.CourseMapper;
import kg.peaksoft.peaksoftlmsbb4.db.mapper.student.StudentMapper;
import kg.peaksoft.peaksoftlmsbb4.db.mapper.teacher.TeacherMapper;
import kg.peaksoft.peaksoftlmsbb4.db.model.Course;
import kg.peaksoft.peaksoftlmsbb4.db.model.Student;
import kg.peaksoft.peaksoftlmsbb4.db.model.Teacher;
import kg.peaksoft.peaksoftlmsbb4.db.repository.CourseRepository;
import kg.peaksoft.peaksoftlmsbb4.db.repository.TeacherRepository;
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
@Transactional
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;
    private final StudentMapper studentMapper;
    private final TeacherMapper teacherMapper;
    private final TeacherService teacherService;
    private final AWSS3Service awss3Service;
    private final TeacherRepository teacherRepository;

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
        Course course = courseRepository.findById(id).orElseThrow(() ->
                new NotFoundException(String.format("course with id = %s not found", id)));
        if (!course.getCourseName().equals(courseRequest.getCourseName())) {
            course.setCourseName(courseRequest.getCourseName());
        }
        if (!course.getDescription().equals(courseRequest.getCourseName())) {
            course.setDescription(courseRequest.getDescription());
        }
        if (!course.getDateOfFinish().isEqual(courseRequest.getDateOfFinish())) {
            course.setDateOfFinish(courseRequest.getDateOfFinish());
        }
        if (!course.getImage().equals(courseRequest.getImage())) {
            course.setImage(courseRequest.getImage());
        }
        log.info("successful update this course:{}", course);
        return courseMapper.deConvert(course);
    }

    @Override
    public CourseResponse delete(Long id) {
        boolean existsById = courseRepository.existsById(id);
        if (!existsById) {
            log.error("not found course with id:{}", id);
            throw new NotFoundException(String.format(" course with id=%s does not exists", id));
        }
        log.info("successful delete course with id:{}", id);
        if (!courseRepository.getById(id).getImage().equals("")) {
            awss3Service.deleteFile(courseRepository.getById(id).getImage());
        }
        Course course = courseRepository.findById(id).orElseThrow(() -> new NotFoundException("course with id %s does not found"));
        courseRepository.deleteById(id);
        log.info("successful delete by this id:{}", id);
        return courseMapper.deConvert(course);
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


    @Override
    @Transactional
    public String assignTeachersToCourse(AssignTeacherRequest assignTeacherRequest) {
        Course course = courseRepository.findById(assignTeacherRequest.getCourseId())
                .orElseThrow(() -> new NotFoundException("Course not found by this id"));
        for (Long id : assignTeacherRequest.getTeacherId()) {
            Teacher instructor = teacherRepository.findById(id).orElseThrow(() ->
                    new NotFoundException("Instructor not found by this id"));
            for (Teacher instructorEntity : course.getTeachers()) {
                if (instructor.getId().equals(instructorEntity.getId())) {
                    throw new AlreadyExistsException(
                            "Instructors with id  already assigned to course");
                }
            }
            course.addTeacher(instructor);
        }
        courseRepository.save(course);
        return String.format("All instructors added to course = %s", course.getCourseName());
    }

    private Course getById(Long id) {
        return courseRepository.findById(id).orElseThrow(() -> new NotFoundException(
                String.format("Course with id=%s does not exists", id)
        ));
    }
}
