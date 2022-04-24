package kg.peaksoft.peaksoftlmsbb4.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.peaksoftlmsbb4.db.dto.presentation.PresentationRequest;
import kg.peaksoft.peaksoftlmsbb4.db.dto.presentation.PresentationResponse;
import kg.peaksoft.peaksoftlmsbb4.db.service.PresentationService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/presentations")
@Tag(name = "Presentations", description = "The Presentations API")
@CrossOrigin(origins = "http//localhost:5000", maxAge = 3600)
public class PresentationsApi {
    private final PresentationService presentationService;

    @PostMapping("/{id}")
    @Operation(summary = "Add new presentation",
            description = "This endpoint save new presentations. Only users with role teacher can add new presentation to lesson")
    @PreAuthorize("hasAnyAuthority('TEACHER')")
    public PresentationResponse savePresentations(@PathVariable Long id,
                                                  @RequestBody PresentationRequest presentationRequest) {
        return presentationService.savePresentation(id, presentationRequest);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Gets a single presentation by identifier",
            description = "For valid response try integer IDs with value >= 1 and...")
    @PreAuthorize("hasAnyAuthority('TEACHER')")
    public PresentationResponse findById(@PathVariable Long id) {
        return presentationService.findById(id);

    }

//    @GetMapping
//    @Operation(summary = "Gets a list", description = "Returns all presentations that are,if there are no presentations,then an error")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200",
//                    description = "Found the presentations",
//                    content = {
//                            @Content(mediaType = "application/json",
//                                    array = @ArraySchema(schema = @Schema(implementation = PresentationsApi.class)))})})
//    @PreAuthorize("hasAnyAuthority('TEACHER')")
//    public List<PresentationResponse> findAll() {
//        return presentationService.findAll();
//    }

    @PutMapping("/{id}")
    @Operation(summary = "Update the presentations",
            description = "Updates the details of an endpoint with ID")
    @PreAuthorize("hasAnyAuthority('TEACHER')")
    public PresentationResponse update(@PathVariable Long id,
                                       @RequestBody PresentationRequest presentationRequest) {
        return presentationService.update(id, presentationRequest);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('TEACHER')")
    @Operation(summary = "Delete the presentation",
            description = "Delete links with ID. Only users with role teacher can delete links")
    public void delete(@PathVariable Long id) {
        presentationService.delete(id);
    }
}
