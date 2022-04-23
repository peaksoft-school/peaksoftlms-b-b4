package kg.peaksoft.peaksoftlmsbb4.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
@Tag(name = "Group", description = "The Group API")
@CrossOrigin(origins = "http//localhost:5000", maxAge = 3600)
@RequestMapping("/api/groups")
public class GroupApi {
    private final GroupService groupService;

    @PostMapping
    @Operation(summary = "Create new group",
            description = "This endpoint save new groups. Only users with role admin can add new groups")
    @PreAuthorize("hasAuthority('ADMIN')")
    public GroupResponse saveGroup(@RequestBody GroupRequest groupRequest) {
        return groupService.saveGroup(groupRequest);
    }

    @PermitAll
    @Operation(summary = "Gets a list",
            description = "Returns all groups that are,if there are no groups,then an error")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Found the groups",
                    content = {
                            @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = GroupApi.class)))})})
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public List<GroupResponse> findAllGroup() {
        return groupService.findAllGroup();
    }


    @GetMapping("/{id}")
    @Operation(summary = "Gets a single groups by identifier",
            description = "For valid response try integer IDs with value >= 1 and...")
    @PreAuthorize("hasAnyAuthority('ADMIN','TEACHER')")
    public GroupResponse findById(@PathVariable Long id) {
        return groupService.findById(id);
    }


    @DeleteMapping("/{id}")
    @Operation(summary = "Delete group with ID",
            description = "Delete group with ID. Only users with role admin can delete courses")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteById(@PathVariable Long id) {
        groupService.deleteById(id);
    }


    @PutMapping("/{id}")
    @Operation(summary = "Update the group",
            description = "Updates the details of an endpoint with ID. Only users with role admin can update the course")
    @PreAuthorize("hasAuthority('ADMIN')")
    public GroupResponse update(@PathVariable Long id, @RequestBody GroupRequest groupRequest) {
        return groupService.update(id, groupRequest);
    }

    @GetMapping("/students/{id}")
    @Operation(summary = "Get teachers with ID",
            description = "Get all students in this groups")
    public List<StudentResponse> getAllStudentByCourseId(@PathVariable Long id) {
        return groupService.getAllStudentByGroupId(id);
    }
}
