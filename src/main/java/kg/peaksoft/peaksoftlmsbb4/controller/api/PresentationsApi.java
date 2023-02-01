package kg.peaksoft.peaksoftlmsbb4.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.peaksoftlmsbb4.controller.payload.request.PresentationRequest;
import kg.peaksoft.peaksoftlmsbb4.controller.payload.response.PresentationResponse;
import kg.peaksoft.peaksoftlmsbb4.db.service.PresentationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/presentations")
@PreAuthorize("hasAnyAuthority('INSTRUCTOR')")
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "Presentations API", description = "The Presentations endpoints")
public class PresentationsApi {

    private final PresentationService presentationService;

    @Operation(summary = "Add new presentation", description = "This endpoint save new presentations. Only users with role teacher can add new presentation to lesson")
    @PostMapping
    public PresentationResponse savePresentations(@RequestBody PresentationRequest presentationRequest) {
        return presentationService.savePresentation(presentationRequest);
    }


    @Operation(summary = "Gets a single presentation by identifier", description = "For valid response try integer IDs with value >= 1 and...")
    @PreAuthorize("hasAnyAuthority('STUDENT')")
    @GetMapping("{id}")
    public PresentationResponse findById(@PathVariable Long id) {
        return presentationService.findById(id);
    }



    @Operation(summary = "Update the presentations", description = "Updates the details of an endpoint with ID")
    @PutMapping("{id}")
    public PresentationResponse update(@PathVariable Long id, @RequestBody PresentationRequest presentationRequest) {
        return presentationService.update(id, presentationRequest);
    }



    @Operation(summary = "Delete the presentation", description = "Delete links with ID. Only users with role teacher can delete links")
    @DeleteMapping("{id}")
    public PresentationResponse delete(@PathVariable Long id) {
        return presentationService.delete(id);
    }


    @Operation(summary = "Gets a single presentation by lesson identifier", description = "For valid response try integer IDs with value >= 1 and...")
    @PreAuthorize("hasAnyAuthority('STUDENT')")
    @GetMapping("LessonPresentation/{id}")
    public PresentationResponse getPresentationByLessonId(@PathVariable Long id) {
        return presentationService.findPresentationByLessonId(id);
    }
}
