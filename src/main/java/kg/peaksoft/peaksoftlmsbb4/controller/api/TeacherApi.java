package kg.peaksoft.peaksoftlmsbb4.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.peaksoftlmsbb4.controller.payload.request.AssignGroupRequest;
import kg.peaksoft.peaksoftlmsbb4.controller.payload.request.AssignStudentRequest;
import kg.peaksoft.peaksoftlmsbb4.controller.payload.request.TeacherRequest;
import kg.peaksoft.peaksoftlmsbb4.controller.payload.response.CourseResponse;
import kg.peaksoft.peaksoftlmsbb4.controller.payload.response.StudentResponse;
import kg.peaksoft.peaksoftlmsbb4.controller.payload.response.TeacherPaginationResponse;
import kg.peaksoft.peaksoftlmsbb4.controller.payload.response.TeacherResponse;
import kg.peaksoft.peaksoftlmsbb4.db.model.User;
import kg.peaksoft.peaksoftlmsbb4.db.service.CourseService;
import kg.peaksoft.peaksoftlmsbb4.db.service.GroupService;
import kg.peaksoft.peaksoftlmsbb4.db.service.StudentService;
import kg.peaksoft.peaksoftlmsbb4.db.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Deque;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/teachers")
@PreAuthorize("hasAnyAuthority('ADMIN')")
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "Teacher API", description = "The Teacher endpoints")
public class TeacherApi {

    private final TeacherService teacherService;
    private final StudentService studentService;
    private final GroupService groupService;
    private final CourseService courseService;


    @Operation(summary = "Gets a list", description = "Returns all teachers that are,if there are no teachers,then an error")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Found the teachers",
                    content = {
                            @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = TeacherApi.class)))})})
    @GetMapping()
    public TeacherPaginationResponse findAll(@RequestParam int page, @RequestParam int size) {
        return teacherService.findAllTeacher(--page, size);
    }

    @Operation(summary = "Add new teacher", description = "This endpoint save new teacher")
    @PostMapping
    public TeacherResponse saveTeacher(@Valid @RequestBody TeacherRequest teacherRequest) {
        return teacherService.saveTeacher(teacherRequest);
    }


    @Operation(summary = "Update the teacher", description = "Updates the details of an endpoint with ID")
    @PatchMapping("{id}")
    public TeacherResponse updateTeacher(@PathVariable("id") Long id, @RequestBody TeacherRequest teacherRequest) {
        return teacherService.updateTeacher(id, teacherRequest);
    }


    @Operation(summary = "Delete the teacher", description = "Delete the teacher with ID")
    @DeleteMapping("{id}")
    public TeacherResponse deleteTeacher(@PathVariable("id") Long id) {
        return teacherService.deleteTeacher(id);
    }

    @Operation(summary = "Teacher's courses", description = "This endpoint for get all teacher's courses")
    @PreAuthorize("hasAnyAuthority('INSTRUCTOR')")
    @GetMapping("teacher-courses")
    public Deque<CourseResponse> teacherCourses(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return teacherService.teacherCourses(user.getEmail());
    }


    @Operation(summary = "Gets a single teacher by identifier", description = "For valid response try integer IDs with value >= 1 and...")
    @GetMapping("{id}")
    public TeacherResponse findById(@PathVariable Long id) {
        return teacherService.findById(id);
    }

    @Operation(summary = "Assign student to course", description = "This endpoint for adding a student to a course. Only user with role teacher can add student to course")
    @PreAuthorize("hasAnyAuthority('INSTRUCTOR')")
    @PostMapping("assign-student")
    public StudentResponse assignStudentToCourse(@RequestBody AssignStudentRequest assignStudentRequest) {
        return studentService.assignStudentToCourse(assignStudentRequest);
    }

    @Operation(summary = "Assign group to course", description = "This endpoint for adding a group to course")
    @PreAuthorize("hasAnyAuthority('INSTRUCTOR')")
    @PostMapping("assign-group")
    public String assignGroupToCourse(@RequestBody AssignGroupRequest assignGroupRequest) {
        return groupService.assignGroupToCourse(assignGroupRequest);
    }

    @Operation(summary = "Course's teachers", description = "Get all teachers ")
    @GetMapping("teachers-by-course/{id}")
    public List<TeacherResponse> getAllTeacherByCourseId(@PathVariable Long id) {
        return courseService.getAllTeacherByCourseId(id);
    }

    @Operation(summary = "find all teachers", description = "This method is a method of adding teachers to a course so you can give how many teachers per course ")
    @GetMapping("unassigned/{id}")
    public Deque<TeacherResponse> teacherResponses(@PathVariable Long id) {
        return teacherService.teacherResponsesForAssign(id);
    }

}
