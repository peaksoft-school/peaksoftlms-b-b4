package kg.peaksoft.peaksoftlmsbb4.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.peaksoftlmsbb4.dto.test.TestRequest;
import kg.peaksoft.peaksoftlmsbb4.dto.test.TestResponse;
import kg.peaksoft.peaksoftlmsbb4.service.TestService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/tests")
@CrossOrigin(origins = "http//localhost:1234", maxAge = 3600)
@Tag(name = "Test", description = "The Test API")
public class TestApi {
    private final TestService testService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/{id}")
    public TestResponse saveTest(@PathVariable Long id, @RequestBody TestRequest testRequest) {
        return testService.saveTest(id, testRequest);
    }

    @PermitAll
    @GetMapping("/{id}")
    public TestResponse findById(@PathVariable Long id) {
        return testService.findById(id);
    }

    @PermitAll
    @GetMapping
    public List<TestResponse> findAllTest() {
        return testService.findAll();
    }

    @PutMapping("{id}")
    @PreAuthorize("hasAuthority('TEACHER')")
    public TestResponse updateTest(@PathVariable Long id, @RequestBody TestRequest testRequest) {
        return testService.update(id, testRequest);
    }

    @PreAuthorize("hasAuthority('TEACHER')")
    @DeleteMapping("/{id}")
    public String deleteTest(@PathVariable Long id) {
        testService.delete(id);
        return String.format("successfully delete this id=%s", id);
    }

}
