package kg.peaksoft.peaksoftlmsbb4.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.peaksoftlmsbb4.controller.payload.request.GroupRequest;
import kg.peaksoft.peaksoftlmsbb4.controller.payload.response.GroupPaginationResponse;
import kg.peaksoft.peaksoftlmsbb4.controller.payload.response.GroupResponse;
import kg.peaksoft.peaksoftlmsbb4.controller.payload.response.StudentResponse;
import kg.peaksoft.peaksoftlmsbb4.db.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/groups")
@CrossOrigin(origins = "*", maxAge = 3600)
@PreAuthorize("hasAnyAuthority('ADMIN')")
@Tag(name = "Group API", description = "The Group endpoints")
public class GroupApi {

    private final GroupService groupService;

    @Operation(summary = "Create new group", description = "This endpoint save new groups. Only users with role admin can add new groups")
    @PostMapping
    public GroupResponse saveGroup(@RequestBody GroupRequest request) {
        return groupService.saveGroup(request);
    }

    @Operation(summary = "Gets a list",
            description = "Returns all groups that are,if there are no groups,then an error")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "All groups with pagination",
                    content = {
                            @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = GroupApi.class)))})})
    @PreAuthorize("hasAnyAuthority('INSTRUCTOR')")
    @GetMapping
    public GroupPaginationResponse getAllForPagination(@RequestParam int page, @RequestParam int size) {
        return groupService.getAllForPagination(--page, size);
    }

    @Operation(summary = "Gets a single groups by identifier", description = "For valid response try integer IDs with value >= 1 and...")
    @PreAuthorize("hasAnyAuthority('INSTRUCTOR')")
    @GetMapping("{id}")
    public GroupResponse findById(@PathVariable Long id) {
        return groupService.findById(id);
    }

    @Operation(summary = "Delete group with ID", description = "Delete group with ID. Only users with role admin can delete courses")
    @DeleteMapping("{id}")
    public GroupResponse deleteById(@PathVariable Long id) {
        return groupService.deleteById(id);
    }

    @Operation(summary = "Update the group", description = "Updates the details of an endpoint with ID. Only users with role admin can update the course")
    @PutMapping("{id}")
    public GroupResponse update(@PathVariable Long id, @RequestBody GroupRequest request) {
        return groupService.update(id, request);
    }

    @Operation(summary = "Get students by group ID", description = "Get all students in this groups")
    @GetMapping("students/{id}")
    public List<StudentResponse> getAllStudentByCourseId(@PathVariable Long id) {
        return groupService.getAllStudentByGroupId(id);
    }

}
