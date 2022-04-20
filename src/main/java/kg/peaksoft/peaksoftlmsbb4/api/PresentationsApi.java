package kg.peaksoft.peaksoftlmsbb4.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.peaksoftlmsbb4.dto.presentation.PresentationRequest;
import kg.peaksoft.peaksoftlmsbb4.dto.presentation.PresentationResponse;
import kg.peaksoft.peaksoftlmsbb4.model.Presentation;
import kg.peaksoft.peaksoftlmsbb4.service.PresentationService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/presentations")
@Tag(name = "Presentations", description = "The Presentations API")
@CrossOrigin(origins = "http//localhost:1234", maxAge = 3600)
public class PresentationsApi {
    private final PresentationService presentationService;

    @PostMapping("/{id}")
    @Operation(summary = "Add new presentation", description = "This method save new presentations")
    @PreAuthorize("hasAnyAuthority('ADMIN','TEACHER')")
    public PresentationResponse savePresentations(@RequestBody PresentationRequest presentationRequest,@PathVariable Long id) {
        return presentationService.savePresentation(id,presentationRequest);
    }

    @GetMapping("/{id}")
    @Operation(summary = "gets a single presentations by identifier")
    @PreAuthorize("hasAnyAuthority('ADMIN','TEACHER')")
    public Presentation findById(@PathVariable Long id) {
        return presentationService.findById(id);

    }

    @GetMapping
    @Operation(summary = "gets a list", description = "Returns all presentations that are,if there are no presentations,then an error")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Found the presentations",
                    content = {
                            @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = PresentationsApi.class)))})})
    @PreAuthorize("hasAnyAuthority('ADMIN','TEACHER')")
    public List<PresentationResponse> findAll() {
        return presentationService.findAll();
    }

    @PutMapping("/{id}")
    @Operation(summary = "update the presentations", description = "Updates the details of an endpoint with ID")
    @PreAuthorize("hasAnyAuthority('ADMIN','TEACHER')")
    public PresentationResponse update(@PathVariable Long id, @RequestBody PresentationRequest presentationRequest) {
        return presentationService.update(id, presentationRequest);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','TEACHER')")
    @Operation(summary = "delete presentations by id")
    public void delete(@PathVariable Long id) {
        presentationService.delete(id);
    }
}
