package kg.peaksoft.peaksoftlmsbb4.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.peaksoftlmsbb4.service.AnswerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/answers")
@CrossOrigin(origins = "http//localhost:1234", maxAge = 3600)
@Tag(name = "Answer", description = "The Answer API")
public class AnswerApi {
    private AnswerService answerService;
}
