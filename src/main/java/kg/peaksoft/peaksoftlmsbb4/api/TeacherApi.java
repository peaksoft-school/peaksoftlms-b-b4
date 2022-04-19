package kg.peaksoft.peaksoftlmsbb4.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.peaksoftlmsbb4.dto.course.CourseResponse;
import kg.peaksoft.peaksoftlmsbb4.dto.student.StudentResponse;
import kg.peaksoft.peaksoftlmsbb4.dto.teacher.TeacherRequest;
import kg.peaksoft.peaksoftlmsbb4.dto.teacher.TeacherResponse;
import kg.peaksoft.peaksoftlmsbb4.service.CourseService;
import kg.peaksoft.peaksoftlmsbb4.service.GroupService;
import kg.peaksoft.peaksoftlmsbb4.service.StudentService;
import kg.peaksoft.peaksoftlmsbb4.service.TeacherService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/teachers")
@CrossOrigin(origins = "http//localhost:1234", maxAge = 3600)
@Tag(name = "Teacher", description = "The Teacher API")
public class TeacherApi {

    private final TeacherService teacherService;
    private final StudentService studentService;
    private final GroupService groupService;
    private final CourseService courseService;

    @GetMapping
    @Operation(summary = "gets a list", description = "Returns all teachers that are,if there are no teachers,then an error")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Found the teachers",
                    content = {
                            @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = TeacherApi.class)))})})
    @PreAuthorize("hasAnyAuthority('ADMIN','TEACHER')")
    public List<TeacherResponse> findAll() {
        return teacherService.findAllTeacher();
    }

    @PostMapping
    @Operation(summary = "Add new teacher", description = "This method save new teacher")
    @PreAuthorize("hasAnyAuthority('ADMIN','TEACHER')")
    public TeacherResponse saveTeacher(@Valid @RequestBody TeacherRequest teacherRequest) {
        return teacherService.saveTeacher(teacherRequest);
    }

    @PutMapping("/{teacherId}")
    @Operation(summary = "update the teacher", description = "Updates the details of an endpoint with ID")
    @PreAuthorize("hasAuthority('ADMIN')")
    public TeacherResponse updateTeacher(@PathVariable("teacherId") Long id, @RequestBody @Valid TeacherRequest teacherRequest) {
        return teacherService.updateTeacher(id, teacherRequest);
    }

    @DeleteMapping("/{teacherId}")
    @Operation(summary = "delete teacher with ID")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteTeacher(@PathVariable("teacherId") Long id) {
        teacherService.deleteTeacher(id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "a method for adding a teacher to a course", description = "a method for adding a teacher to a course")
    @PostMapping("assignTeacher/{courseId}")
    public void assignTeacherToCourse(@PathVariable Long courseId, @RequestParam(required = false) Long teacherId) {
        teacherService.assignTeacherToCourse(courseId, teacherId);
    }

    @Operation(summary = "teacher's course", description = "Use this method to get the whole course from this method teacher")
    @GetMapping("teacherCourses/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','TEACHER')")
    public List<CourseResponse> teacherCourses(@PathVariable Long id) {
        return teacherService.teacherCourses(id);
    }

    @Operation(summary = "gets a single teacher by identifier")
    @PermitAll
    @GetMapping("/{id}")
    public TeacherResponse findById(@PathVariable Long id){
        return teacherService.findById(id);
    }

    @PostMapping("assignStudent/{courseId}")
    @PreAuthorize("hasAnyAuthority('ADMIN','TEACHER')")
    public void assignStudentToCourse(@PathVariable Long courseId,
                                      @RequestParam (required = false)Long studentId){
        studentService.assignStudentToCourse(courseId,studentId);
    }

    @PostMapping("assignGroup/{courseId}")
    @PreAuthorize("hasAnyAuthority('ADMIN','TEACHER')")
    public void assignGroupToCourse(@PathVariable Long courseId,
                                    @RequestParam(required = false)Long groupId){
        groupService.assignGroupToCourse(courseId,groupId);
    }

    @GetMapping("teachersByCourseId/{id}")
    public List<TeacherResponse> getAllTeacherByCourseId(@PathVariable Long id){
        return courseService.getAllTeacherByCourseId(id);
    }

}
