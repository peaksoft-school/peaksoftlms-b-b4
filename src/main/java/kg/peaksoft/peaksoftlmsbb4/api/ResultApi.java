package kg.peaksoft.peaksoftlmsbb4.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.peaksoftlmsbb4.db.dto.result.AnswerRequest;
import kg.peaksoft.peaksoftlmsbb4.db.dto.result.ResultResponse;
import kg.peaksoft.peaksoftlmsbb4.db.model.User;
import kg.peaksoft.peaksoftlmsbb4.db.service.ResultService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/results")
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "Result", description = "The Result API")
public class ResultApi {
    private final ResultService resultService;

    @PostMapping
    @PreAuthorize("hasAnyAuthority('STUDENT')")
    public ResultResponse saveResult(Authentication authentication, @RequestBody AnswerRequest answerRequest){
        User user = (User) authentication.getPrincipal();
        return resultService.saveResult(answerRequest,user.getEmail());
    }
}
