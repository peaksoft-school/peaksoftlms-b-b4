package kg.peaksoft.peaksoftlmsbb4.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.peaksoftlmsbb4.db.dto.course.CourseResponse;
import kg.peaksoft.peaksoftlmsbb4.db.dto.result.GetResultResponse;
import kg.peaksoft.peaksoftlmsbb4.db.dto.result.ResultRequest;
import kg.peaksoft.peaksoftlmsbb4.db.dto.result.ResultResponse;
import kg.peaksoft.peaksoftlmsbb4.db.dto.student.AssignStudentRequest;
import kg.peaksoft.peaksoftlmsbb4.db.model.User;
import kg.peaksoft.peaksoftlmsbb4.db.service.ResultService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/api/results")
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "Result", description = "The  Result API")
public class ResultApi {
    private final ResultService resultService;

    @PostMapping()
    @PreAuthorize("hasAuthority('STUDENT')")
    public ResultResponse save(
            @Valid Authentication authentication,
            @RequestBody ResultRequest resultRequest) {
        User user = (User) authentication.getPrincipal();
        return resultService.saveResult(user.getUsername(),resultRequest);
    }

    @GetMapping
    public List<ResultResponse> findAll() {
        return resultService.findAll();
    }

    @GetMapping("{id}")
    public ResultResponse findById(@Valid @PathVariable Long id) {
        return resultService.findById(id);
    }

    @DeleteMapping("{id}")
    public String deleteById(@Valid @PathVariable Long id) {
        resultService.delete(id);
        return String.format("successful delete this %s", id);
    }

    @GetMapping("/results12")
    @PreAuthorize("hasAuthority('STUDENT')")
    public GetResultResponse resultsResponse(@Valid  Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return resultService.getResults(user.getUsername());
    }



}
