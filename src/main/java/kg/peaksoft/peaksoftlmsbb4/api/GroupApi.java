package kg.peaksoft.peaksoftlmsbb4.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Groups Api ", description = "this group api")
@CrossOrigin(origins = "http//localhost:1234", maxAge = 3600)
@RequestMapping("/api/groups")
public class GroupApi {
    private final GroupService groupService;

    @PostMapping("/save/{id}")
    @Operation(summary = "save group")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public GroupResponse saveGroup(@PathVariable Long id, @RequestBody GroupRequest groupRequest) {
        return groupService.saveGroup(id, groupRequest);
    }

    @PermitAll
    @GetMapping
    @Operation(summary = "find All group")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<GroupResponse> findAllGroup() {
        return groupService.findAllGroup();
    }


    @GetMapping("/{id}")
    @Operation(summary = "find by id group")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','TEACHER')")
    public GroupResponse findById(@PathVariable Long id) {
        return groupService.findById(id);
    }


    @DeleteMapping("/{id}")
    @Operation(summary = "delete by id group")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void deleteById(@PathVariable Long id) {
        groupService.deleteById(id);
    }


    @PatchMapping("/{id}")
    @Operation(summary = "update group")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public GroupResponse update(@PathVariable Long id, @RequestBody GroupRequest groupRequest) {
        return groupService.update(id, groupRequest);
    }

    @GetMapping("/group/{id}")
    @Operation(summary = "Find the teacher of the group")
    public List<StudentResponse> getAllTeacherByCourseId(@PathVariable Long id) {
        return groupService.getAllStudentByGroupId(id);
    }
}
