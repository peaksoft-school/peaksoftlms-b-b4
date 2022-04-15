package kg.peaksoft.peaksoftlmsbb4.api;

import kg.peaksoft.peaksoftlmsbb4.dto.student.StudentRequest;
import kg.peaksoft.peaksoftlmsbb4.dto.student.StudentResponse;
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
    @PreAuthorize("hasAnyAuthority('ADMIN','TEACHER')")
    public List<StudentResponse> findAll() {
        return studentService.findAllStudent();
    }

    @PostMapping("/saveStudent")
    @PreAuthorize("hasAuthority('ADMIN')")
    public StudentResponse saveStudent(@RequestBody @Valid StudentRequest studentRequest) {
        return studentService.saveStudent(studentRequest);
    }

    @PutMapping("/updateStudent/{studentId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public StudentResponse updateStudent(@PathVariable("studentId") Long id, @RequestBody @Valid StudentRequest studentRequest) {
        return studentService.updateStudent(id, studentRequest);
    }

    @DeleteMapping("deleteStudent/{studentId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteStudent(@PathVariable("studentId") Long id) {
        studentService.deleteStudent(id);
    }


}
