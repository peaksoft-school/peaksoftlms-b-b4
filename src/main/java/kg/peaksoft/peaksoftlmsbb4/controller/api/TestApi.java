package kg.peaksoft.peaksoftlmsbb4.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.peaksoftlmsbb4.controller.payload.request.SwitcherRequest;
import kg.peaksoft.peaksoftlmsbb4.controller.payload.request.TestRequest;
import kg.peaksoft.peaksoftlmsbb4.controller.payload.response.TestResponse;
import kg.peaksoft.peaksoftlmsbb4.db.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/tests")
@PreAuthorize("hasAuthority('INSTRUCTOR')")
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "Test API", description = "The Test endpoints")
public class TestApi {

    private final TestService testService;

    @Operation(summary = "Add new test", description = "This endpoint create new test. Only users with role teacher can add new task to lesson")
    @PostMapping()
    public TestResponse saveTest(@RequestBody TestRequest request) {
        return testService.saveTest(request);
    }

    @Operation(summary = "Gets a single test by identifier", description = "For valid response try integer IDs with value >= 1 and...")
    @PreAuthorize("hasAnyAuthority('STUDENT')")
    @GetMapping("{id}")
    public TestResponse findById(@PathVariable Long id) {
        return testService.findById(id);
    }

    @Operation(summary = "Find test by lesson ID", description = "Gets a single tasks by lesson identifier")
    @PreAuthorize("hasAnyAuthority('STUDENT')")
    @GetMapping("lesson/{id}")
    public TestResponse findByLessonId(@PathVariable Long id) {
        return testService.findByLessonId(id);
    }

    @Operation(summary = "Update test", description = "Updates the details of an endpoint with ID")
    @PutMapping("{id}")
    public TestResponse updateTest(@PathVariable Long id, @RequestBody TestRequest request) {
        return testService.update(id, request);
    }

    @Operation(summary = "Delete test", description = "Delete the test with id")
    @DeleteMapping("{id}")
    public String deleteTest(@PathVariable Long id) {
        testService.delete(id);
        return String.format("successfully delete this id=%s", id);
    }

    @Operation(summary = "Switcher", description = "With this endpoint you can disable or enable test")
    @PutMapping("switcher/{id}")
    public boolean switcher(@PathVariable Long id, @RequestBody SwitcherRequest request) {
        return testService.switcher(id, request);
    }

}
