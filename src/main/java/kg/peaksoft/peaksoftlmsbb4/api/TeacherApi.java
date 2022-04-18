package kg.peaksoft.peaksoftlmsbb4.api;

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
public class TeacherApi {

    private final TeacherService teacherService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','TEACHER')")
    public List<TeacherResponse> findAll() {
        return teacherService.findAllTeacher();
    }

    @PostMapping("/saveTeacher/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','TEACHER')")
    public TeacherResponse saveTeacher(@Valid @PathVariable Long id, @RequestBody TeacherRequest teacherRequest) {
        return teacherService.saveTeacher(teacherRequest);
    }

    @PutMapping("/updateTeacher/{teacherId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public TeacherResponse updateTeacher(@PathVariable("teacherId") Long id, @RequestBody @Valid TeacherRequest teacherRequest) {
        return teacherService.updateTeacher(id, teacherRequest);
    }

    @DeleteMapping("deleteTeacher/{teacherId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void deleteTeacher(@PathVariable("teacherId") Long id) {
        teacherService.deleteTeacher(id);
    }


}
