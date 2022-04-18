package kg.peaksoft.peaksoftlmsbb4.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.peaksoftlmsbb4.dto.course.CourseRequest;
import kg.peaksoft.peaksoftlmsbb4.dto.course.CourseResponse;
import kg.peaksoft.peaksoftlmsbb4.dto.student.StudentResponse;
import kg.peaksoft.peaksoftlmsbb4.dto.teacher.TeacherResponse;
import kg.peaksoft.peaksoftlmsbb4.model.Course;
import kg.peaksoft.peaksoftlmsbb4.service.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/courses")
@CrossOrigin(origins = "http//localhost:1234", maxAge = 3600)
@Tag(name = "Course", description = "The  Course API")
public class CourseApi {
    private final CourseService courseService;

    @Operation(summary = "Create new course", description = "This method saves new courses")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @PostMapping("/save")
    public CourseResponse saveCourse(@RequestBody CourseRequest courseRequest) {
        return courseService.saveCourse(courseRequest);
    }

    @Operation(summary = "Gets a single courses by identifier", description = "For valid response try integer IDs with value >= 1 and...")
    @GetMapping("/{id}")
    public Course findById(@PathVariable Long id) {
        return courseService.findById(id);
    }

    @Operation(summary = "Gets a list", description = "Returns all courses that are,if there are no courses,then an error")
    @GetMapping
    public List<CourseResponse> findAllCourse() {
        return courseService.findAll();
    }

    @Operation(summary = "Updates the course:", description = "Updates the details of an endpoint with ID ")
    @PutMapping("/{id}")
    public CourseResponse update(@PathVariable Long id, @RequestBody CourseRequest courseRequest) {
        return courseService.update(id, courseRequest);
    }

    @Operation(summary = "delete the courses ", description = "Deletes courses with id ")
    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable Long id) {
        courseService.delete(id);

    }

    @Operation(summary = "Get students by course id", description = "Get all students in this course")
    @GetMapping("/students/{id}")
    public List<StudentResponse> getAllStudentByCourseId(@PathVariable Long id) {
        return courseService.getAllStudentsByCourseId(id);
    }

    @Operation(summary = "Get teachers with ID", description = "Get all teachers in this course")
    @GetMapping("/teachers/{id}")
    public List<TeacherResponse> getAllTeacherByCourseId(@PathVariable Long id) {
        return courseService.getAllTeacherByCourseId(id);

    }

}
