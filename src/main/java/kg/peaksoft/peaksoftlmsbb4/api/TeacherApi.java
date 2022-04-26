package kg.peaksoft.peaksoftlmsbb4.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.peaksoftlmsbb4.db.dto.course.CourseResponse;
import kg.peaksoft.peaksoftlmsbb4.db.dto.group.AssignGroupRequest;
import kg.peaksoft.peaksoftlmsbb4.db.dto.student.AssignStudentRequest;
import kg.peaksoft.peaksoftlmsbb4.db.dto.teacher.TeacherRequest;
import kg.peaksoft.peaksoftlmsbb4.db.dto.teacher.TeacherResponse;
import kg.peaksoft.peaksoftlmsbb4.db.model.User;
import kg.peaksoft.peaksoftlmsbb4.db.service.CourseService;
import kg.peaksoft.peaksoftlmsbb4.db.service.GroupService;
import kg.peaksoft.peaksoftlmsbb4.db.service.StudentService;
import kg.peaksoft.peaksoftlmsbb4.db.service.TeacherService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/teachers")
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "Teacher", description = "The Teacher API")
public class TeacherApi {

    private final TeacherService teacherService;
    private final StudentService studentService;
    private final GroupService groupService;
    private final CourseService courseService;

    @GetMapping("/All")
    @Operation(summary = "Gets a list",
            description = "Returns all teachers that are,if there are no teachers,then an error")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Found the teachers",
                    content = {
                            @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = TeacherApi.class)))})})
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public List<TeacherResponse> findAll() {
        return teacherService.findAllTeacher();
    }


    @Operation(summary = "Add new teacher",
            description = "This endpoint save new teacher")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping
    public TeacherResponse saveTeacher(@Valid @RequestBody TeacherRequest teacherRequest) {
        return teacherService.saveTeacher(teacherRequest);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update the teacher",
            description = "Updates the details of an endpoint with ID")
    @PreAuthorize("hasAuthority('ADMIN')")
    public TeacherResponse updateTeacher(@PathVariable("id") Long id, @RequestBody @Valid TeacherRequest teacherRequest) {
        return teacherService.updateTeacher(id, teacherRequest);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete the teacher",
            description = "Delete the teacher with ID")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteTeacher(@PathVariable("id") Long id) {
        teacherService.deleteTeacher(id);
    }

    @Operation(summary = "Teacher's courses",
            description = "This endpoint for get all teacher's courses")
    @GetMapping("/teacherCourses")
    @PreAuthorize("hasAnyAuthority('TEACHER')")
    public List<CourseResponse> teacherCourses(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return teacherService.teacherCourses(user.getEmail());
    }


    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @Operation(summary = "Gets a single teacher by identifier",
            description = "For valid response try integer IDs with value >= 1 and...")
    @GetMapping("/{id}")
    public TeacherResponse findById(@PathVariable Long id) {
        return teacherService.findById(id);
    }

    @Operation(summary = "Assign student to course",
            description = "This endpoint for adding a student to a course. Only user with role teacher can add student to course")
    @PostMapping("/assignStudent")
    @PreAuthorize("hasAnyAuthority('TEACHER')")
    public void assignStudentToCourse(@RequestBody AssignStudentRequest assignStudentRequest,
                                      @RequestParam(value = "studentId",required = false) Long studentId) {
        studentService.assignStudentToCourse(assignStudentRequest, studentId);
    }

    @Operation(summary = "Assign group to course",
            description = "This endpoint for adding a group to course")
    @PostMapping("/assignGroup")
    @PreAuthorize("hasAnyAuthority('ADMIN','TEACHER')")
    public void assignGroupToCourse(@RequestBody AssignGroupRequest assignGroupRequest,
                                    @RequestParam(value = "groupId",required = false) Long groupId) {
        groupService.assignGroupToCourse(assignGroupRequest, groupId);
    }

    @Operation(summary = "Course's teachers",
            description = "Get all teachers ")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("teachersByCourseId/{id}")
    public List<TeacherResponse> getAllTeacherByCourseId(@PathVariable Long id) {
        return courseService.getAllTeacherByCourseId(id);
    }

}
