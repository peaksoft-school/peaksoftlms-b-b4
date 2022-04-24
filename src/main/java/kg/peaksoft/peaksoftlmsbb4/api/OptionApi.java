package kg.peaksoft.peaksoftlmsbb4.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.peaksoftlmsbb4.dto.option.OptionRequest;
import kg.peaksoft.peaksoftlmsbb4.dto.option.OptionResponse;
import kg.peaksoft.peaksoftlmsbb4.service.OptionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/options")
@Tag(name = "Options", description = "The Option API")
@CrossOrigin(origins = "http//localhost:5000", maxAge = 3600)
public class OptionApi {
    private final OptionService optionService;

    @PostMapping("/{id}")
    public OptionResponse save(@PathVariable Long id, @RequestBody OptionRequest optionRequest) {
        return optionService.saveOption(id, optionRequest);
    }

    @GetMapping
    public List<OptionResponse> findAll() {
        return optionService.findAll();
    }

    @GetMapping("/{id}")
    public OptionResponse findById(@PathVariable Long id) {
        return optionService.findById(id);
    }


    @PutMapping("/{id}")
    public OptionResponse update(@PathVariable Long id, @RequestBody OptionRequest optionRequest) {
        return optionService.update(id, optionRequest);
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable Long id) {
        optionService.delete(id);
        return String.format("successful delete this id=%s", id);
    }
}
