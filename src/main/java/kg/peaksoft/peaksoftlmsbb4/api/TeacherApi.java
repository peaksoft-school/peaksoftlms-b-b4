package kg.peaksoft.peaksoftlmsbb4.api;

import io.jsonwebtoken.impl.crypto.MacProvider;
import kg.peaksoft.peaksoftlmsbb4.dto.TeacherRequest;
import kg.peaksoft.peaksoftlmsbb4.dto.TeacherResponse;
import kg.peaksoft.peaksoftlmsbb4.service.TeacherService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/teachers")
@AllArgsConstructor
public class TeacherApi {

    private final TeacherService teacherService;

    @PostMapping("/register")
    public TeacherResponse register(@RequestBody @Valid TeacherRequest teacherRequest){
        return teacherService.register(teacherRequest);
    }


    @DeleteMapping("/delete/{teacherId}")
    public void deleteTeacherById(@PathVariable("teacherId") Long id){
       teacherService.deleteById(id);
    }

}
