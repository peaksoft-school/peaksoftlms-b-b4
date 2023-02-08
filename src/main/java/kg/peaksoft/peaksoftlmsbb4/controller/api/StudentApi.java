package kg.peaksoft.peaksoftlmsbb4.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.peaksoftlmsbb4.controller.payload.request.AnswerRequest;
import kg.peaksoft.peaksoftlmsbb4.controller.payload.request.StudentRequest;
import kg.peaksoft.peaksoftlmsbb4.controller.payload.response.AnswerResponse;
import kg.peaksoft.peaksoftlmsbb4.controller.payload.response.CourseResponse;
import kg.peaksoft.peaksoftlmsbb4.controller.payload.response.StudentPaginationResponse;
import kg.peaksoft.peaksoftlmsbb4.controller.payload.response.StudentResponse;
import kg.peaksoft.peaksoftlmsbb4.db.enums.StudyFormat;
import kg.peaksoft.peaksoftlmsbb4.db.model.User;
import kg.peaksoft.peaksoftlmsbb4.db.service.ResultService;
import kg.peaksoft.peaksoftlmsbb4.db.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.Deque;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/students")
@PreAuthorize("hasAuthority('ADMIN')")
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "Student API", description = "The Student endpoints")
public class StudentApi {

    private final StudentService studentService;

    private final ResultService resultService;

    @Operation(summary = "Gets a list", description = "Returns all students that are,if there are no students,then an error")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Found the students",
                    content = {
                            @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = StudentApi.class)))})})
    @PreAuthorize("hasAnyAuthority('INSTRUCTOR')")
    @GetMapping
    public StudentPaginationResponse getAll(@RequestParam int page, @RequestParam int size, @RequestParam StudyFormat studyFormat) {
        return studentService.getAll(--page, size, studyFormat);
    }

    @Operation(summary = "Add new student", description = "This endpoint save new student. Only users with role admin can add new students")
    @PostMapping
    public StudentResponse saveStudent(@RequestBody @Valid StudentRequest request) {
        return studentService.saveStudent(request);
    }

    @Operation(summary = "Update the students", description = "Updates the details of an endpoint with ID.  Only users with role admin can update students")
    @PutMapping("{id}")
    public StudentResponse updateStudent(@PathVariable("id") Long id, @RequestBody @Valid StudentRequest request) {
        return studentService.updateStudent(id, request);
    }

    @Operation(summary = "Delete the student", description = "Delete student with ID")
    @DeleteMapping("{id}")
    public StudentResponse deleteStudent(@PathVariable("id") Long id) {
        return studentService.deleteStudent(id);
    }

    @Operation(summary = "Gets a single student by identifier", description = "For valid response try integer IDs with value >= 1 and...")
    @GetMapping("{id}")
    public StudentResponse findById(@PathVariable Long id) {
        return studentService.findById(id);
    }

    @Operation(summary = "Get by name", description = "Get student by name")
    @GetMapping("get-by-name")
    public Deque<StudentResponse> getByStudentName(@RequestParam String name) {
        return studentService.findByStudentName(name);
    }

    @Operation(summary = "Import EXCEL", description = "This endpoint for import students list from excel to group")
    @PostMapping("import")
    public List<StudentResponse> importExcelFile(@RequestParam(name = "file") MultipartFile files, @RequestParam Long groupId) throws Exception {
        return studentService.importExcelFile(files, groupId);
    }

    @PreAuthorize("hasAnyAuthority('STUDENT')")
    @GetMapping("student-courses")
    public Deque<CourseResponse> studentCourses(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return studentService.getStudentCourses(user.getEmail());
    }

    @Operation(summary = "Pass the test", description = "This endpoint for pass the test")
    @PreAuthorize("hasAnyAuthority('STUDENT')")
    @PostMapping("test")
    public AnswerResponse saveResult(Authentication authentication, @RequestBody AnswerRequest request) {
        User user = (User) authentication.getPrincipal();
        return resultService.saveResult(request, user.getEmail());
    }

}

