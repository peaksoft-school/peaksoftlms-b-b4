package kg.peaksoft.peaksoftlmsbb4.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.peaksoftlmsbb4.db.dto.question.QuestionRequest;
import kg.peaksoft.peaksoftlmsbb4.db.dto.question.QuestionResponse;
import kg.peaksoft.peaksoftlmsbb4.db.service.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/api/questions")
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "Question", description = "The Question API")
public class QuestionApi {
    private final QuestionService questionService;

    @Operation(summary = "Gets a single question by identifier",
            description = "For valid response try integer IDs with value >= 1 and...")
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('INSTRUCTOR')")
    public QuestionResponse findById(@Valid @PathVariable Long id) {
        return questionService.findById(id);
    }

    @Operation(summary = "Update the question",
            description = "Updates the details of an endpoint with ID")
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('INSTRUCTOR')")
    public QuestionResponse updateQuestion(
            @Valid @PathVariable Long id, @RequestBody QuestionRequest questionRequest) {
        return questionService.update(id, questionRequest);
    }

    @Operation(summary = "Delete question",
            description = "Delete the question with ID")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('INSTRUCTOR')")
    public String deleteById(@Valid @PathVariable Long id) {
        questionService.delete(id);
        return String.format("successfully delete this id=%s", id);
    }
}
