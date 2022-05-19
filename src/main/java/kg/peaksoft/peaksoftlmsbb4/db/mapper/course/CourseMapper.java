package kg.peaksoft.peaksoftlmsbb4.db.mapper.course;

import kg.peaksoft.peaksoftlmsbb4.db.converter.Converter;
import kg.peaksoft.peaksoftlmsbb4.db.dto.course.CourseRequest;
import kg.peaksoft.peaksoftlmsbb4.db.dto.course.CourseResponse;
import kg.peaksoft.peaksoftlmsbb4.db.model.Course;
import kg.peaksoft.peaksoftlmsbb4.exceptions.BadRequestException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class CourseMapper implements Converter<Course, CourseRequest, CourseResponse> {

    @Override
    public Course convert(CourseRequest courseRequest) {
        Course course = new Course();
        course.setCourseName(courseRequest.getCourseName());
        course.setDescription(courseRequest.getDescription());
        course.setImage(courseRequest.getImage());
        course.setDateOfStart(LocalDate.now());
        if (courseRequest.getDateOfFinish().isBefore(LocalDate.now())){
            throw new BadRequestException("Date of finish can't be before than date of start");
        }else {
            course.setDateOfFinish(courseRequest.getDateOfFinish());
        }
        return course;
    }

    @Override
    public CourseResponse deConvert(Course course) {
        CourseResponse courseResponse = new CourseResponse();
        courseResponse.setId(course.getId());
        courseResponse.setCourseName(course.getCourseName());
        courseResponse.setDescription(course.getDescription());
        courseResponse.setImage(course.getImage());
        courseResponse.setDuration(course.getDateOfStart().getYear() +"-"+ course.getDateOfFinish().getYear());
        courseResponse.setDateOfFinish(course.getDateOfFinish());
        return courseResponse;
    }

    public List<CourseResponse> deConvert(List<Course> courses) {
        List<CourseResponse> courseResponses = new ArrayList<>();
        for (Course c : courses) {
            courseResponses.add(deConvert(c));
        }
        return courseResponses;
    }
}