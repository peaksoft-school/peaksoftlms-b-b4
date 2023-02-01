package kg.peaksoft.peaksoftlmsbb4.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.peaksoftlmsbb4.controller.payload.request.TaskRequest;
import kg.peaksoft.peaksoftlmsbb4.controller.payload.response.TaskResponse;
import kg.peaksoft.peaksoftlmsbb4.db.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/tasks")
@PreAuthorize("hasAnyAuthority('INSTRUCTOR')")
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "Task API", description = "The Tasks endpoints")
public class TaskApi {

    private final TaskService taskService;

    @Operation(summary = "Add new tasks", description = "This endpoint save new task. Only users with role teacher can add new task to lesson")
    @PostMapping
    public TaskResponse saveTasks(@RequestBody TaskRequest request) {
        return taskService.saveTasks(request);
    }

    @Operation(summary = "Gets a single task by lesson identifier", description = "For valid response try integer IDs with value >= 1 and...")
    @PreAuthorize("hasAnyAuthority('STUDENT')")
    @GetMapping("{id}")
    public TaskResponse findTaskByLessonId(@PathVariable Long id) {
        return taskService.findTaskByLessonId(id);
    }

    @Operation(summary = "Update the tasks", description = "Updates the details of an endpoint with ID")
    @PutMapping("{id}")
    public TaskResponse update(@PathVariable Long id, @RequestBody TaskRequest request) {
        return taskService.update(id, request);
    }

    @Operation(summary = "Delete the task", description = "Delete task with ID")
    @DeleteMapping("{id}")
    public TaskResponse delete(@PathVariable Long id) {
        return taskService.delete(id);
    }

    @Operation(summary = "Gets a single task by lesson identifier", description = "For valid response try integer IDs with value >= 1 and...")
    @PreAuthorize("hasAnyAuthority('STUDENT')")
    @GetMapping("task/{id}")
    public TaskResponse getById(@PathVariable Long id) {
        return taskService.getById(id);
    }

}
