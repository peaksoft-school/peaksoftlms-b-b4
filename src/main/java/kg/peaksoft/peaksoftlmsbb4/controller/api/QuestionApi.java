package kg.peaksoft.peaksoftlmsbb4.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.peaksoftlmsbb4.controller.payload.request.QuestionRequest;
import kg.peaksoft.peaksoftlmsbb4.controller.payload.response.QuestionResponse;
import kg.peaksoft.peaksoftlmsbb4.db.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/questions")
@PreAuthorize("hasAuthority('INSTRUCTOR')")
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "Question API", description = "The Question endpoints")
public class QuestionApi {

    private final QuestionService questionService;

    @Operation(summary = "Gets a single question by identifier", description = "For valid response try integer IDs with value >= 1 and...")
    @GetMapping("{id}")
    public QuestionResponse findById(@Valid @PathVariable Long id) {
        return questionService.findById(id);
    }

    @Operation(summary = "Update the question", description = "Updates the details of an endpoint with ID")
    @PutMapping("{id}")
    public QuestionResponse updateQuestion(@Valid @PathVariable Long id, @RequestBody QuestionRequest request) {
        return questionService.update(id, request);
    }

    @Operation(summary = "Delete question", description = "Delete the question with ID")
    @DeleteMapping("{id}")
    public String deleteById(@Valid @PathVariable Long id) {
        questionService.delete(id);
        return String.format("successfully delete this id=%s", id);
    }

}
