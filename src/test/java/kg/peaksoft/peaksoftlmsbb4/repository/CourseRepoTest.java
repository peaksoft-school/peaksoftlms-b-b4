//package kg.peaksoft.peaksoftlmsbb4.repository;
//
//import kg.peaksoft.peaksoftlmsbb4.model.Course;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//
//import java.time.LocalDate;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@DataJpaTest
//@Slf4j
//public class CourseRepoTest {
//
//    @Autowired
//    private CourseRepository courseRepository;
//
//    @Test
//    void findCourseByEmailExistsTest() {
//        String name = "java";
//        Course course = new Course(name, "image", "description", LocalDate.now());
//        Course save = courseRepository.save(course);
//        log.info("Course save to database successfully {}",save);
//
//        boolean expected = courseRepository.existsByCourseName("java");
//        log.info("Course with name [{}] founded from database",expected);
//        assertThat(expected).isTrue();
//    }
//
//    @Test
//    void findCourseByEmailDoesNotExistsTest() {
//        String name = "javasrip";
//        boolean expected = courseRepository.existsByCourseName(name);
//        log.info("Course with name [{}] founded from database",expected);
//        assertThat(expected).isFalse();
//    }
//}
