package kg.peaksoft.peaksoftlmsbb4.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.peaksoftlmsbb4.db.dto.course.CourseResponse;
import kg.peaksoft.peaksoftlmsbb4.db.dto.student.StudentRequest;
import kg.peaksoft.peaksoftlmsbb4.db.dto.student.StudentResponse;
import kg.peaksoft.peaksoftlmsbb4.db.enums.StudyFormat;
import kg.peaksoft.peaksoftlmsbb4.db.model.Student;
import kg.peaksoft.peaksoftlmsbb4.db.model.User;
import kg.peaksoft.peaksoftlmsbb4.db.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/students")
@AllArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "Student", description = "The Student API")
public class StudentApi {

    private final StudentService studentService;

    @GetMapping
    @Operation(summary = "Gets a list", description = "Returns all students that are,if there are no students,then an error")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Found the students",
                    content = {
                            @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = StudentApi.class)))})})
    @PreAuthorize("hasAnyAuthority('ADMIN','TEACHER')")
    public List<StudentResponse> findAll(@RequestParam int size,
                                         @RequestParam int page) {
        return studentService.findAllStudent(PageRequest.of(size, page, Sort.by("studentName")));
    }

    @PostMapping
    @Operation(summary = "Add new student",
            description = "This endpoint save new student. Only users with role admin can add new students")
    @PreAuthorize("hasAuthority('ADMIN')")
    public StudentResponse saveStudent(@Valid @RequestBody StudentRequest studentRequest) {
        return studentService.saveStudent(studentRequest);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update the students",
            description = "Updates the details of an endpoint with ID.  Only users with role admin can update students")
    @PreAuthorize("hasAuthority('ADMIN')")
    public StudentResponse updateStudent(@PathVariable("id") Long id, @RequestBody @Valid StudentRequest studentRequest) {
        return studentService.updateStudent(id, studentRequest);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete the student",
            description = "Delete student with ID")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteStudent(@PathVariable("id") Long id) {
        studentService.deleteStudent(id);
    }

    @GetMapping("/format")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "Get student by study format",
            description = "Here you can choose the format of training")
    public List<StudentResponse> getStudyFormat(@RequestParam(required = false) StudyFormat studyFormat) {
        return studentService.findByStudyFormat(studyFormat);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "Gets a single  student by identifier", description = "For valid response try integer IDs with value >= 1 and...")
    public StudentResponse findById(@PathVariable Long id) {
        return studentService.findById(id);
    }

    @GetMapping("/getByName")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "Get by name", description = "Get student by name")
    public List<Student> getByStudentName(@RequestParam String name) {
        return studentService.findByStudentName(name);
    }

    @Operation(summary = "import EXCEL",
            description = "This endpoint for import students list from excel to group")
    @PostMapping("/import")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public List<StudentResponse> importExcelFile
            (@RequestParam("groupId") Long id, @RequestParam(name = "file") MultipartFile files) throws IOException {
        return studentService.importExcelFile(files, id);
    }

    @GetMapping("/studentCourses")
    @PreAuthorize("hasAnyAuthority('STUDENT')")
    public List<CourseResponse> studentCourses(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return studentService.studentCourses(user.getEmail());
    }


}

