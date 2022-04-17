package kg.peaksoft.peaksoftlmsbb4.api;

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
public class StudentApi {

    private final StudentService studentService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','TEACHER')")
    public List<StudentResponse> findAll() {
        return studentService.findAllStudent();
    }

    @PostMapping("/saveStudent")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public StudentResponse saveStudent(@RequestBody @Valid StudentRequest studentRequest) {
        return studentService.saveStudent(studentRequest);
    }

    @PutMapping("/updateStudent/{studentId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public StudentResponse updateStudent(@PathVariable("studentId") Long id, @RequestBody @Valid StudentRequest studentRequest) {
        return studentService.updateStudent(id, studentRequest);
    }

    @DeleteMapping("deleteStudent/{studentId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void deleteStudent(@PathVariable("studentId") Long id) {
        studentService.deleteStudent(id);
    }

    @GetMapping("/format")
    public List<StudentResponse> getStudyFormat(@RequestParam(required = false) StudyFormat studyFormat) {
        return studentService.findByStudyFormat(studyFormat);
    }


}
