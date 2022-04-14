package kg.peaksoft.peaksoftlmsbb4.service.impl;

import kg.peaksoft.peaksoftlmsbb4.dto.course.CourseRequest;
import kg.peaksoft.peaksoftlmsbb4.dto.course.CourseResponse;
import kg.peaksoft.peaksoftlmsbb4.exception.NotFoundException;
import kg.peaksoft.peaksoftlmsbb4.mapper.CourseMapper;
import kg.peaksoft.peaksoftlmsbb4.model.Course;
import kg.peaksoft.peaksoftlmsbb4.repository.CourseRepository;
import kg.peaksoft.peaksoftlmsbb4.service.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    @Override
    public CourseResponse saveCourse(CourseRequest courseRequest) {
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
}

