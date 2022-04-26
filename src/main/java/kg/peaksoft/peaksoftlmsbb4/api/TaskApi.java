package kg.peaksoft.peaksoftlmsbb4.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.peaksoftlmsbb4.db.dto.task.TaskRequest;
import kg.peaksoft.peaksoftlmsbb4.db.dto.task.TaskResponse;
import kg.peaksoft.peaksoftlmsbb4.db.model.Task;
import kg.peaksoft.peaksoftlmsbb4.db.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/tasks")
@Tag(name = "Task", description = "The Tasks API")
@CrossOrigin(origins = "*", maxAge = 3600)
public class TaskApi {
    private final TaskService taskService;

    @PostMapping("/{id}")
    @Operation(summary = "Add new tasks",
            description = "This endpoint save new task. Only users with role teacher can add new task to lesson")
    @PreAuthorize("hasAnyAuthority('TEACHER')")
    public TaskResponse saveTasks(@PathVariable Long id, @RequestBody TaskRequest taskRequest) {
        return taskService.saveTasks(id, taskRequest);

    }

    @GetMapping("/{id}")
    @Operation(summary = "Gets a single tasks by identifier",
            description = "For valid response try integer IDs with value >= 1 and...")
    @PreAuthorize("hasAnyAuthority('TEACHER')")
    public Task findById(@PathVariable Long id) {
        return taskService.findById(id);
    }

//    @GetMapping
//    @Operation(summary = "Gets a list",
//            description = "Returns all tasks that are,if there are no tasks,then an error")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200",
//                    description = "Found the tasks",
//                    content = {
//                            @Content(mediaType = "application/json",
//                                    array = @ArraySchema(schema = @Schema(implementation = TaskApi.class)))})})
//    @PreAuthorize("hasAnyAuthority('TEACHER')")
//    public List<TaskResponse> findAll() {
//        return taskService.findAll();
//    }

    @PutMapping("/{id}")
    @Operation(summary = "Update the tasks",
            description = "Updates the details of an endpoint with ID")
    @PreAuthorize("hasAnyAuthority('TEACHER')")
    public TaskResponse update(@PathVariable Long id, @RequestBody TaskRequest taskRequest) {
        return taskService.update(id, taskRequest);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('TEACHER')")
    @Operation(summary = "Delete the task",
            description = "Delete task with ID")
    public void delete(@PathVariable Long id) {
        taskService.delete(id);
    }
}
