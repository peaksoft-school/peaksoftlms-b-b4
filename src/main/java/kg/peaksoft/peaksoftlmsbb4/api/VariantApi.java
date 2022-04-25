package kg.peaksoft.peaksoftlmsbb4.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.peaksoftlmsbb4.db.dto.variant.VariantRequest;
import kg.peaksoft.peaksoftlmsbb4.db.dto.variant.VariantResponse;
import kg.peaksoft.peaksoftlmsbb4.db.service.VariantService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/variants")
@CrossOrigin(origins = "http//localhost:1234", maxAge = 3600)
@Tag(name = "Variant", description = "The Variant API")
public class VariantApi {
    private final VariantService variantService;

    @PostMapping("/{id}")
    public VariantResponse saveVariant(@PathVariable Long id, @RequestBody VariantRequest variantRequest) {
        return variantService.saveVariant(id, variantRequest);
    }

    @GetMapping
    public List<VariantResponse> findAll() {
        return variantService.findAll();
    }

    @GetMapping("/{id}")
    public VariantResponse findById(@PathVariable Long id) {
        return variantService.findById(id);
    }

    @PutMapping("/{id}")
    public VariantResponse updateVariant(@PathVariable Long id, @RequestBody VariantRequest variantRequest) {
        return variantService.update(id, variantRequest);
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable Long id) {
        variantService.delete(id);
        return String.format("successful variant delete by id=%s", id);
    }
}
