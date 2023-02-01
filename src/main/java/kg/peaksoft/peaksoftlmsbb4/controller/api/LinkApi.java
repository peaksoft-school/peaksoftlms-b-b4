package kg.peaksoft.peaksoftlmsbb4.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.peaksoftlmsbb4.controller.payload.request.LinkRequest;
import kg.peaksoft.peaksoftlmsbb4.controller.payload.response.LinkResponse;
import kg.peaksoft.peaksoftlmsbb4.db.service.LinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/links")
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "Links API", description = "The Links endpoints")
public class LinkApi {

    private final LinkService linkService;

    @Operation(summary = "Add new link", description = "This endpoint save new links to lesson. Only users with role teacher can add new link to lesson")
    @PreAuthorize("hasAnyAuthority('INSTRUCTOR')")
    @PostMapping
    public LinkResponse saveLink(@RequestBody LinkRequest request) {
        return linkService.saveLinks(request);
    }

    @Operation(summary = "Gets a single links by identifier", description = "For valid response try integer IDs with value >= 1 and...")
    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR','STUDENT')")
    @GetMapping("{id}")
    public LinkResponse findById(@PathVariable Long id) {
        return linkService.findById(id);
    }

    @Operation(summary = "Update the link", description = "Updates the details of an endpoint with ID. Only users with role teacher can update the link")
    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    @PutMapping("{id}")
    public LinkResponse update(@PathVariable Long id, @RequestBody LinkRequest request) {
        return linkService.update(id, request);
    }

    @Operation(summary = "Delete the link", description = "Delete links with ID. Only users with role teacher can delete links")
    @PreAuthorize("hasAnyAuthority('INSTRUCTOR')")
    @DeleteMapping("{id}")
    public LinkResponse delete(@PathVariable Long id) {
        return linkService.delete(id);
    }

    @Operation(summary = "Gets a single links by lesson identifier", description = "For valid response try integer IDs with value >= 1 and...")
    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR','STUDENT')")
    @GetMapping("lesson/{id}")
    public LinkResponse findByLessonId(@PathVariable Long id) {
        return linkService.findLinkByLessonId(id);
    }

}
