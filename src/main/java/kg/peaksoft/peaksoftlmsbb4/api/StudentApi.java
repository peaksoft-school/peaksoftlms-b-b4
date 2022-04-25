package kg.peaksoft.peaksoftlmsbb4.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.peaksoftlmsbb4.db.dto.student.StudentRequest;
import kg.peaksoft.peaksoftlmsbb4.db.dto.student.StudentResponse;
import kg.peaksoft.peaksoftlmsbb4.db.enums.StudyFormat;
import kg.peaksoft.peaksoftlmsbb4.db.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/students")
@AllArgsConstructor
@CrossOrigin(origins = "http//localhost:5000", maxAge = 3600)
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
        return studentService.findAllStudent(PageRequest.of(size, page));
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
    public StudentResponse findById(@PathVariable Long id){
        return studentService.findById(id);
    }


}
