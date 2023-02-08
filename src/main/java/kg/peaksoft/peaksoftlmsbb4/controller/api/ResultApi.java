package kg.peaksoft.peaksoftlmsbb4.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.peaksoftlmsbb4.controller.payload.request.AnswerRequest;
import kg.peaksoft.peaksoftlmsbb4.controller.payload.response.AnswerResponse;
import kg.peaksoft.peaksoftlmsbb4.controller.payload.response.TestResultResponse;
import kg.peaksoft.peaksoftlmsbb4.db.model.User;
import kg.peaksoft.peaksoftlmsbb4.db.service.ResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/results")
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "Result API", description = "The Result endpoints")
public class ResultApi {

    private final ResultService resultService;

    @Operation(summary = "Past the test", description = "This endpoint for pass the test")
    @PreAuthorize("hasAnyAuthority('STUDENT')")
    @PostMapping
    public AnswerResponse saveResult(Authentication authentication, @RequestBody AnswerRequest request) {
        User user = (User) authentication.getPrincipal();
        return resultService.saveResult(request, user.getEmail());
    }

    @Operation(summary = "Get results", description = "This endpoint get results by test ID")
    @PreAuthorize("hasAnyAuthority('INSTRUCTOR')")
    @GetMapping("{id}")
    public TestResultResponse getResults(@PathVariable Long id) {
        return resultService.getResults(id);
    }

}
