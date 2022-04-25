package kg.peaksoft.peaksoftlmsbb4.service;

import kg.peaksoft.peaksoftlmsbb4.db.dto.course.CourseRequest;
import kg.peaksoft.peaksoftlmsbb4.db.dto.course.CourseResponse;
import kg.peaksoft.peaksoftlmsbb4.db.model.Course;
import kg.peaksoft.peaksoftlmsbb4.db.repository.CourseRepository;
import kg.peaksoft.peaksoftlmsbb4.db.service.CourseService;
import kg.peaksoft.peaksoftlmsbb4.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
public class CourseServiceTest {

    @Autowired
    private CourseService underTest;

    @Autowired
    private CourseRepository courseRepository;

    @BeforeEach
    void setUp() {
        courseRepository.deleteAll();
    }

    @AfterEach
    void tearDown() {
        courseRepository.deleteAll();
    }

    @Test
    void addNewCourseTest() {
        // given
        CourseRequest courseRequest = new CourseRequest(
                "Java 5",
                "this is image",
                " this is description",
                LocalDate.now()
        );

        CourseResponse courseResponse = underTest.saveCourse(courseRequest);
        // when
        int result = courseRepository.getQuantityOfAllCourses();
        // then
        Assertions.assertEquals(1, result);
    }

    @Test
    void getCourseById(){
        // give
        CourseRequest courseRequest = new CourseRequest(
                "Java 5",
                "this is image",
                " this is description",
                LocalDate.now()
        );

        underTest.saveCourse(courseRequest);
        // when
        Course course = courseRepository.findById(1L).orElseThrow(() -> new NotFoundException("not found"));
        // then
        assertThat(course.getId()).isEqualTo(1L);
    }

    @Test
    void deleteCourseById(){
        Course course = new Course(
                "Java 5",
                "this is image",
                " this is description",
                LocalDate.now()
        );
        courseRepository.save(course);
        underTest.delete(4L);

        boolean expected = courseRepository.existsByCourseName("Java 5");

        assertThat(expected).isFalse();
    }

    @Test
    void getAllCourse(){
        Course course = new Course(
                "Java 5",
                "this is image",
                " this is description",
                LocalDate.now()
        );

        courseRepository.save(course);

        List<CourseResponse> responseList = underTest.findAll();
        assertThat(responseList.size()).isGreaterThan(0);
    }

    @Test
    void updateCourseById(){
        Course course = new Course(
                "Java 5",
                "this is image",
                " this is description",
                LocalDate.now()
        );
        courseRepository.save(course);
        Course course1 = courseRepository.findById(5L).orElseThrow(() -> new NotFoundException("not found"));
        course1.setCourseName("Java 6");
        course1.setImage("image changed");
        course1.setDescription("description changed");

        assertThat(course1.getCourseName()).isEqualTo("Java 6");
    }
}
