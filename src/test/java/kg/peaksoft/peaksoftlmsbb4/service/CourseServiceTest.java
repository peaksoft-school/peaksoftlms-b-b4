//package kg.peaksoft.peaksoftlmsbb4.service;
//
//import kg.peaksoft.peaksoftlmsbb4.dto.course.CourseRequest;
//import kg.peaksoft.peaksoftlmsbb4.dto.course.CourseResponse;
//import kg.peaksoft.peaksoftlmsbb4.mapper.course.CourseMapper;
//import kg.peaksoft.peaksoftlmsbb4.model.Course;
//import kg.peaksoft.peaksoftlmsbb4.repository.CourseRepository;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//
//import java.time.LocalDate;
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@SpringBootTest
//@Slf4j
//public class CourseServiceTest {
//
//    @Autowired
//    private CourseService underTest;
//
//    @Autowired
//    private CourseRepository courseRepository;
//
//    @BeforeEach
//    void setUp() {
//        courseRepository.deleteAll();
//    }
//
//    @AfterEach
//    void tearDown() {
//        courseRepository.deleteAll();
//    }
//
//    @Test
//    void addNewCourseTest() {
//        // given
//        CourseRequest courseRequest = new CourseRequest(
//                "Java 5",
//                "this is image",
//                " this is description",
//                LocalDate.now()
//        );
//
//        CourseResponse courseResponse = underTest.saveCourse(courseRequest);
//        // when
//        int result = courseRepository.getQuantityOfAllCourses();
//        // then
//        Assertions.assertEquals(1, result);
//    }
//
//    @Test
//    void getCourseById(){
//        // give
//        CourseRequest courseRequest = new CourseRequest(
//                "Java 5",
//                "this is image",
//                " this is description",
//                LocalDate.now()
//        );
//
//        underTest.saveCourse(courseRequest);
//        // when
//        Course course = underTest.findById(1L);
//        // then
//        assertThat(course.getId()).isEqualTo(1L);
//    }
//
//    @Test
//    void deleteCourseById(){
//        Course course = new Course(
//                "Java 5",
//                "this is image",
//                " this is description",
//                LocalDate.now()
//        );
//        courseRepository.save(course);
//        underTest.delete(4L);
//
//        boolean expected = courseRepository.existsByCourseName("Java 5");
//
//        assertThat(expected).isFalse();
//    }
//
//    @Test
//    void getAllCourse(){
//        Course course = new Course(
//                "Java 5",
//                "this is image",
//                " this is description",
//                LocalDate.now()
//        );
//
//        courseRepository.save(course);
//
//        List<CourseResponse> responseList = underTest.findAll();
//        assertThat(responseList.size()).isGreaterThan(0);
//    }
//
//    @Test
//    void updateCourseById(){
//        Course course = new Course(
//                "Java 5",
//                "this is image",
//                " this is description",
//                LocalDate.now()
//        );
//        courseRepository.save(course);
//
//        Course course1 = underTest.findById(5L);
//        course1.setCourseName("Java 6");
//        course1.setImage("image changed");
//        course1.setDescription("description changed");
//
//        Course course2 = courseRepository.save(course1);
//        assertThat(course2.getCourseName()).isEqualTo("Java 6");
//    }
//}
