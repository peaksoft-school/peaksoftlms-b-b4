package kg.peaksoft.peaksoftlmsbb4.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.peaksoftlmsbb4.service.TestService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/tests")
@CrossOrigin(origins = "http//localhost:1234", maxAge = 3600)
@Tag(name = "Test", description = "The Test API")
public class TestApi {
    private final TestService testService;


}
