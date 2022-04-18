package kg.peaksoft.peaksoftlmsbb4.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.peaksoftlmsbb4.dto.student.StudentRequest;
import kg.peaksoft.peaksoftlmsbb4.dto.student.StudentResponse;
import kg.peaksoft.peaksoftlmsbb4.enums.StudyFormat;
import kg.peaksoft.peaksoftlmsbb4.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/students")
@AllArgsConstructor
@CrossOrigin(origins = "http//localhost:1234", maxAge = 3600)
@Tag(name = "Student", description = "The Student API")
public class StudentApi {

    private final StudentService studentService;

    @GetMapping
    @Operation(summary = "gets a list", description = "Returns all students that are,if there are no students,then an error")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','TEACHER')")
    public List<StudentResponse> findAll() {
        return studentService.findAllStudent();
    }

    @PostMapping("/saveStudent")
    @Operation(summary = "Add new student", description = "This method save new student")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public StudentResponse saveStudent(@RequestBody @Valid StudentRequest studentRequest) {
        return studentService.saveStudent(studentRequest);
    }

    @PutMapping("/updateStudent/{studentId}")
    @Operation(summary = "update the students", description = "Updates the details of an endpoint with ID")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public StudentResponse updateStudent(@PathVariable("studentId") Long id, @RequestBody @Valid StudentRequest studentRequest) {
        return studentService.updateStudent(id, studentRequest);
    }

    @DeleteMapping("deleteStudent/{studentId}")
    @Operation(summary = "delete students with ID")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void deleteStudent(@PathVariable("studentId") Long id) {
        studentService.deleteStudent(id);
    }

    @GetMapping("/format")
    @Operation(summary = "here you can choose the format of training")
    public List<StudentResponse> getStudyFormat(@RequestParam(required = false) StudyFormat studyFormat) {
        return studentService.findByStudyFormat(studyFormat);
    }


}
