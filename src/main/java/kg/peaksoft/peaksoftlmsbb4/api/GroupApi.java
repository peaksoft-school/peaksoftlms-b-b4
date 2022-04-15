package kg.peaksoft.peaksoftlmsbb4.api;

import kg.peaksoft.peaksoftlmsbb4.dto.group.GroupRequest;
import kg.peaksoft.peaksoftlmsbb4.dto.group.GroupResponse;
import kg.peaksoft.peaksoftlmsbb4.dto.student.StudentResponse;
import kg.peaksoft.peaksoftlmsbb4.service.GroupService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http//localhost:1234", maxAge = 3600)
@RequestMapping("/api/group")
public class GroupApi {
    private final GroupService groupService;

    @PostMapping("/save/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public GroupResponse saveGroup(@PathVariable Long id, @RequestBody GroupRequest groupRequest) {
        return groupService.saveGroup(id, groupRequest);
    }

    @PermitAll
    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<GroupResponse> findAllGroup() {
        return groupService.findAllGroup();
    }


    @GetMapping("/find/by/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','TEACHER')")
    public GroupResponse findById(@PathVariable Long id) {
        return groupService.findById(id);
    }


    @DeleteMapping("/delete/by/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void deleteById(@PathVariable Long id) {
        groupService.deleteById(id);
    }


    @PatchMapping("update/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public GroupResponse update(@PathVariable Long id, @RequestBody GroupRequest groupRequest) {
        return groupService.update(id, groupRequest);
    }

    @GetMapping("/group/{id}")
    public List<StudentResponse> getAllTeacherByCourseId(@PathVariable Long id) {
        return groupService.getAllStudentByGroupId(id);
    }
}
