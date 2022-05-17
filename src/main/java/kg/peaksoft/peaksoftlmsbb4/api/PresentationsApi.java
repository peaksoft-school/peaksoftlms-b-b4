package kg.peaksoft.peaksoftlmsbb4.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.peaksoftlmsbb4.db.dto.presentation.PresentationRequest;
import kg.peaksoft.peaksoftlmsbb4.db.dto.presentation.PresentationResponse;
import kg.peaksoft.peaksoftlmsbb4.db.service.PresentationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("api/presentations")
@Tag(name = "Presentations", description = "The Presentations API")
@CrossOrigin(origins = "*", maxAge = 3600)
public class PresentationsApi {

    private final PresentationService presentationService;

    @PostMapping
    @Operation(summary = "Add new presentation",
            description = "This endpoint save new presentations. Only users with role teacher can add new presentation to lesson")
    @PreAuthorize("hasAnyAuthority('INSTRUCTOR')")
    public PresentationResponse savePresentations(@RequestBody PresentationRequest presentationRequest) {
        return presentationService.savePresentation(presentationRequest);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Gets a single presentation by identifier",
            description = "For valid response try integer IDs with value >= 1 and...")
    @PreAuthorize("hasAnyAuthority('INSTRUCTOR')")
    public PresentationResponse findById(@PathVariable Long id) {
        return presentationService.findById(id);

    }

    @PutMapping("/{id}")

    @Operation(summary = "Update the presentations",
            description = "Updates the details of an endpoint with ID")
    @PreAuthorize("hasAnyAuthority('INSTRUCTOR')")
    public PresentationResponse update(@PathVariable Long id,
                                       @RequestBody PresentationRequest presentationRequest) {
        return presentationService.update(id, presentationRequest);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('INSTRUCTOR')")
    @Operation(summary = "Delete the presentation",
            description = "Delete links with ID. Only users with role teacher can delete links")
    public ResponseEntity<Map<String,Long>> delete(@PathVariable Long id) {
        Map<String,Long> response = new HashMap<>();
        response.put("id",presentationService.delete(id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
