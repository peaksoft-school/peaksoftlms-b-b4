package kg.peaksoft.peaksoftlmsbb4.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.peaksoftlmsbb4.db.dto.result.AnswerRequest;
import kg.peaksoft.peaksoftlmsbb4.db.dto.result.AnswerResponse;
import kg.peaksoft.peaksoftlmsbb4.db.dto.result.ResultResponse;
import kg.peaksoft.peaksoftlmsbb4.db.dto.result.TestResultResponse;
import kg.peaksoft.peaksoftlmsbb4.db.model.User;
import kg.peaksoft.peaksoftlmsbb4.db.service.ResultService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/results")
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "Result", description = "The Result API")
public class ResultApi {
    private final ResultService resultService;

    @PostMapping
    @Operation(summary = "Past the test",
            description = "This endpoint for pass the test")
    @PreAuthorize("hasAnyAuthority('STUDENT')")
    public AnswerResponse saveResult(Authentication authentication, @RequestBody AnswerRequest answerRequest) {
        User user = (User) authentication.getPrincipal();
        return resultService.saveResult(answerRequest, user.getEmail());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get results",
            description = "This endpoint get results by test ID")
    @PreAuthorize("hasAnyAuthority('INSTRUCTOR')")
    public TestResultResponse getResults(@PathVariable Long id) {
        return resultService.getResults(id);
    }
}
