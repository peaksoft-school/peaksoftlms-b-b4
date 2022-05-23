package kg.peaksoft.peaksoftlmsbb4.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.peaksoftlmsbb4.db.dto.course.CourseResponse;
import kg.peaksoft.peaksoftlmsbb4.db.dto.result.AnswerRequest;
import kg.peaksoft.peaksoftlmsbb4.db.dto.result.AnswerResponse;
import kg.peaksoft.peaksoftlmsbb4.db.dto.student.StudentPaginationResponse;
import kg.peaksoft.peaksoftlmsbb4.db.dto.student.StudentRequest;
import kg.peaksoft.peaksoftlmsbb4.db.dto.student.StudentResponse;
import kg.peaksoft.peaksoftlmsbb4.db.enums.StudyFormat;
import kg.peaksoft.peaksoftlmsbb4.db.model.User;
import kg.peaksoft.peaksoftlmsbb4.db.service.ResultService;
import kg.peaksoft.peaksoftlmsbb4.db.service.StudentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.Deque;
import java.util.List;

@RestController
@RequestMapping("/api/students")
@AllArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "Student", description = "The Student API")
@Slf4j
public class StudentApi {

    private final StudentService studentService;
    private final ResultService resultService;

    @GetMapping
    @Operation(summary = "Gets a list",
            description = "Returns all students that are,if there are no students,then an error")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Found the students",
                    content = {
                            @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = StudentApi.class)))})})
    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    public StudentPaginationResponse getAll(@RequestParam int page,
                                            @RequestParam int size,
                                            @RequestParam StudyFormat studyFormat) {

        return studentService.getAll(--page, size, studyFormat);
    }

    @PostMapping
    @Operation(summary = "Add new student",
            description = "This endpoint save new student. Only users with role admin can add new students")
    @PreAuthorize("hasAuthority('ADMIN')")
    public StudentResponse saveStudent(@RequestBody @Valid StudentRequest studentRequest) {
        return studentService.saveStudent(studentRequest);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update the students",
            description = "Updates the details of an endpoint with ID.  Only users with role admin can update students")
    @PreAuthorize("hasAuthority('ADMIN')")
    public StudentResponse updateStudent(@PathVariable("id") Long id,
                                         @RequestBody @Valid StudentRequest studentRequest) {
        return studentService.updateStudent(id, studentRequest);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete the student",
            description = "Delete student with ID")
    @PreAuthorize("hasAuthority('ADMIN')")
    public StudentResponse deleteStudent(@PathVariable("id") Long id) {
        return studentService.deleteStudent(id);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "Gets a single student by identifier",
            description = "For valid response try integer IDs with value >= 1 and...")
    public StudentResponse findById(@PathVariable Long id) {
        return studentService.findById(id);
    }

    @GetMapping("/getByName")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "Get by name",
            description = "Get student by name")
    public Deque<StudentResponse> getByStudentName(@RequestParam String name) {
        return studentService.findByStudentName(name);
    }

    @Operation(summary = "Import EXCEL",
            description = "This endpoint for import students list from excel to group")
    @PostMapping("/import")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public List<StudentResponse> importExcelFile(@RequestParam(name = "file") MultipartFile files, @RequestParam Long groupId) throws Exception {
        return studentService.importExcelFile(files, groupId);
    }

    @GetMapping("/studentCourses")
    @PreAuthorize("hasAnyAuthority('STUDENT')")
    public Deque<CourseResponse> studentCourses(Authentication authentication){
        User user = (User) authentication.getPrincipal();
        return studentService.getStudentCourses(user.getEmail());
    }

    @PostMapping("test")
    @Operation(summary = "Pass the test",
            description = "This endpoint for pass the test")
    @PreAuthorize("hasAnyAuthority('STUDENT')")
    public AnswerResponse saveResult(Authentication authentication, @RequestBody AnswerRequest answerRequest){
        User user = (User) authentication.getPrincipal();
        return resultService.saveResult(answerRequest,user.getEmail());
    }
}

