package kg.peaksoft.peaksoftlmsbb4.mapper;

import kg.peaksoft.peaksoftlmsbb4.converter.Converter;
import kg.peaksoft.peaksoftlmsbb4.dto.course.CourseRequest;
import kg.peaksoft.peaksoftlmsbb4.dto.course.CourseResponse;
import kg.peaksoft.peaksoftlmsbb4.model.Course;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CourseMapper implements Converter<Course, CourseRequest, CourseResponse> {

    @Override
    public Course convert(CourseRequest courseRequest) {
        Course course = new Course();
        course.setCourseName(courseRequest.getCourseName());
        course.setDescription(courseRequest.getDescription());
        course.setImage(courseRequest.getImage());
        course.setDateOfStart(courseRequest.getDateOfStart());
        return course;
    }

    @Override
    public CourseResponse deConvert(Course course) {
        CourseResponse courseResponse = new CourseResponse();
        courseResponse.setId(course.getId());
        courseResponse.setCourseName(course.getCourseName());
        courseResponse.setDescription(course.getDescription());
        courseResponse.setImage(course.getImage());
        courseResponse.setDateOfStart(course.getDateOfStart());
        return courseResponse;
    }

    public void update(Course course, CourseRequest courseRequest) {
        if (!course.getCourseName().equals(courseRequest.getCourseName())) {
            course.setCourseName(courseRequest.getCourseName());
        }
        if (!course.getDescription().equals(courseRequest.getCourseName())) {
            course.setDescription(courseRequest.getDescription());
        }
        if (!course.getImage().equals(courseRequest.getImage())) {
            course.setImage(courseRequest.getImage());
        }
    }
}