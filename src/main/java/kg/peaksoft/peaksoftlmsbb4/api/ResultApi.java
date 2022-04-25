package kg.peaksoft.peaksoftlmsbb4.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.peaksoftlmsbb4.db.dto.result.ResultRequest;
import kg.peaksoft.peaksoftlmsbb4.db.dto.result.ResultResponse;
import kg.peaksoft.peaksoftlmsbb4.db.service.ResultService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/results")
@Tag(name = "Result", description = "The Results API")
@CrossOrigin(origins = "http//localhost:5000", maxAge = 3600)
public class ResultApi {
    private final ResultService resultService;

    @PostMapping("/{id}")
    public ResultResponse save(@PathVariable Long id, @RequestBody ResultRequest resultRequest) {
        return resultService.saveResult(id, resultRequest);
    }

    @GetMapping("/{id}")
    public ResultResponse findById(@PathVariable Long id) {
        return resultService.findById(id);
    }

    @GetMapping
    public List<ResultResponse> findAll() {
        return resultService.findAll();
    }

    @PutMapping("/{id}")
    public ResultResponse update(@PathVariable Long id, @RequestBody ResultRequest resultRequest) {
        return resultService.update(id, resultRequest);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        resultService.delete(id);
        return String.format("successful delete this id=%s", id);
    }

    @GetMapping("/result")
    public List<Long> results() {
        return resultService.findAllByResultFalseOrderByResult();
    }
}
