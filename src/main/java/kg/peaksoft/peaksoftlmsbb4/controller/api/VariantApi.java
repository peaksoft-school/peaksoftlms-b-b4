package kg.peaksoft.peaksoftlmsbb4.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.peaksoftlmsbb4.controller.payload.request.VariantRequest;
import kg.peaksoft.peaksoftlmsbb4.controller.payload.response.VariantResponse;
import kg.peaksoft.peaksoftlmsbb4.db.service.VariantService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/variants")
@PreAuthorize("hasAnyAuthority('INTRUCTOR,STUDENT')")
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "Variant API", description = "The Variant endpoints")
public class VariantApi {

    private final VariantService variantService;

    @Operation(summary = "Gets a single variant by identifier", description = "For valid response try integer IDs with value >= 1 and...")
    @GetMapping("{id}")
    public VariantResponse findById(@Valid @PathVariable Long id) {
        return variantService.findById(id);
    }

    @Operation(summary = "Update the variant", description = "Updates the details of an endpoint with ID")
    @PutMapping("{id}")
    public VariantResponse updateVariant(@Valid @PathVariable Long id, @RequestBody VariantRequest request) {
        return variantService.update(id, request);
    }

    @Operation(summary = "Delete variant", description = "Delete the variant by ID")
    @DeleteMapping("{id}")
    public String deleteById(@Valid @PathVariable Long id) {
        variantService.delete(id);
        return String.format("successful variant delete by id=%s", id);
    }

    @GetMapping
    public List<VariantResponse> findAll() {
        return variantService.findAll();
    }

}
