package kg.peaksoft.peaksoftlmsbb4.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.peaksoftlmsbb4.db.dto.question.QuestionRequest;
import kg.peaksoft.peaksoftlmsbb4.db.dto.question.QuestionResponse;
import kg.peaksoft.peaksoftlmsbb4.db.service.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/questions")
@CrossOrigin(origins = "http//localhost:1234", maxAge = 3600)
@Tag(name = "Question", description = "The Question API")
public class QuestionApi {
    private final QuestionService questionService;

    @PostMapping("/{id}")
    public QuestionResponse saveQuestion(@PathVariable Long id, @RequestBody QuestionRequest questionRequest) {
        return questionService.saveQuestion(id, questionRequest);
    }

    @GetMapping
    public List<QuestionResponse> findAllQuestion() {
        return questionService.findAll();
    }

    @GetMapping("/{id}")
    public QuestionResponse findById(@PathVariable Long id) {
        return questionService.findById(id);
    }

    @PutMapping("/{id}")
    public QuestionResponse updateQuestion(@PathVariable Long id, @RequestBody QuestionRequest questionRequest) {
        return questionService.update(id, questionRequest);
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable Long id) {
        questionService.delete(id);
        return String.format("successfully delete this id=%s", id);
    }

}
