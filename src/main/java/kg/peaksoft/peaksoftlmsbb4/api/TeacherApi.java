package kg.peaksoft.peaksoftlmsbb4.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.peaksoftlmsbb4.dto.teacher.TeacherRequest;
import kg.peaksoft.peaksoftlmsbb4.dto.teacher.TeacherResponse;
import kg.peaksoft.peaksoftlmsbb4.service.TeacherService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/teachers")
@CrossOrigin(origins = "http//localhost:1234", maxAge = 3600)
@Tag(name = "Teacher", description = "The Teacher API")
public class TeacherApi {

    private final TeacherService teacherService;

    @GetMapping
    @Operation(summary = "gets a list", description = "Returns all teachers that are,if there are no teachers,then an error")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','TEACHER')")
    public List<TeacherResponse> findAll() {
        return teacherService.findAllTeacher();
    }

    @PostMapping("/saveTeacher/{id}")
    @Operation(summary = "Add new teacher", description = "This method save new teacher")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','TEACHER')")
    public TeacherResponse saveTeacher(@Valid @PathVariable Long id, @RequestBody TeacherRequest teacherRequest) {
        return teacherService.saveTeacher(id, teacherRequest);
    }

    @PutMapping("/updateTeacher/{teacherId}")
    @Operation(summary = "update the teacher", description = "Updates the details of an endpoint with ID")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public TeacherResponse updateTeacher(@PathVariable("teacherId") Long id, @RequestBody @Valid TeacherRequest teacherRequest) {
        return teacherService.updateTeacher(id, teacherRequest);
    }

    @DeleteMapping("deleteTeacher/{teacherId}")
    @Operation(summary = "delete teacher with ID")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void deleteTeacher(@PathVariable("teacherId") Long id) {
        teacherService.deleteTeacher(id);
    }

}
