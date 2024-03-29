package kg.peaksoft.peaksoftlmsbb4.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.peaksoftlmsbb4.controller.payload.request.AssignTeacherRequest;
import kg.peaksoft.peaksoftlmsbb4.controller.payload.request.CourseRequest;
import kg.peaksoft.peaksoftlmsbb4.controller.payload.response.CoursePaginationResponse;
import kg.peaksoft.peaksoftlmsbb4.controller.payload.response.CourseResponse;
import kg.peaksoft.peaksoftlmsbb4.controller.payload.response.StudentResponse;
import kg.peaksoft.peaksoftlmsbb4.controller.payload.response.TeacherResponse;
import kg.peaksoft.peaksoftlmsbb4.db.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/courses")
@CrossOrigin(origins = "*", maxAge = 3600)
@PreAuthorize("hasAuthority('ADMIN')")
@Tag(name = "Course API", description = "The Course endpoints")
public class CourseApi {

    private final CourseService courseService;

    @Operation(summary = "Create new course", description = "This endpoint saves new courses. Only users with role admin can add new courses")
    @PostMapping()
    public CourseResponse saveCourse(@RequestBody CourseRequest request) {
        return courseService.saveCourse(request);
    }

    @Operation(summary = "Gets a single courses by identifier", description = "For valid response try integer IDs with value >= 1 and...")
    @PreAuthorize("hasAnyAuthority('INSTRUCTOR','STUDENT')")
    @GetMapping("{id}")
    public CourseResponse findById(@PathVariable Long id) {
        return courseService.findById(id);
    }

    @Operation(summary = "Get a list",
            description = "Returns all courses that are,if there are no courses,then an error")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "All courses with pagination",
                    content = {
                            @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = CourseApi.class)))})})
    @PreAuthorize("hasAnyAuthority('INSTRUCTOR')")
    @GetMapping
    public CoursePaginationResponse getAllCoursesForPagination(@RequestParam int page, @RequestParam int size) {
        return courseService.coursesForPagination(--page, size);
    }

    @Operation(summary = "Updates the course", description = "Updates the details of an endpoint with ID. Only users with role admin can update the course")
    @PutMapping("{id}")
    public CourseResponse update(@PathVariable Long id, @RequestBody CourseRequest request) {
        return courseService.update(id, request);
    }

    @Operation(summary = "Delete the course ", description = "Delete course with id. Only users with role admin can delete courses")
    @DeleteMapping("{id}")
    public CourseResponse deleteCourse(@PathVariable Long id) {
        return courseService.delete(id);
    }

    @Operation(summary = "Get students by course id", description = "Get all students in this course")
    @GetMapping("students/{id}")
    @PreAuthorize("hasAnyAuthority('INSTRUCTOR')")
    public List<StudentResponse> getAllStudentByCourseId(@PathVariable Long id) {
        return courseService.getAllStudentsByCourseId(id);
    }

    @Operation(summary = "Get teachers by course ID", description = "Get all teachers in this course")
    @GetMapping("teachers/{id}")
    public List<TeacherResponse> getAllTeacherByCourseId(@PathVariable Long id) {
        return courseService.getAllTeacherByCourseId(id);
    }

    @Operation(summary = "Assign teacher to course", description = "This endpoint for adding a teacher to a course. Only user with role admin can add teacher to course")
    @PostMapping("assign-teachers")
    public String assignTeacherToCourse(@RequestBody AssignTeacherRequest request) {
        return courseService.assignTeachersToCourse(request);
    }

}
