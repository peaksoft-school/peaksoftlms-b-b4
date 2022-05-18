package kg.peaksoft.peaksoftlmsbb4.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.peaksoftlmsbb4.db.dto.link.LinkRequest;
import kg.peaksoft.peaksoftlmsbb4.db.dto.link.LinkResponse;
import kg.peaksoft.peaksoftlmsbb4.db.service.LinkService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("api/links")
@Tag(name = "Links", description = "The Links API")
@CrossOrigin(origins = "*", maxAge = 3600)
public class LinkApi {

    private final LinkService linkService;

    @PostMapping
    @Operation(summary = "Add new link",
            description = "This endpoint save new links to lesson. Only users with role teacher can add new link to lesson")
    @PreAuthorize("hasAnyAuthority('INSTRUCTOR')")
    public LinkResponse saveLink(@RequestBody LinkRequest linkRequest) {
        return linkService.saveLinks(linkRequest);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Gets a single links by identifier",
            description = "For valid response try integer IDs with value >= 1 and...")
    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    public LinkResponse findById(@PathVariable Long id) {
        return linkService.findById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update the link",
            description = "Updates the details of an endpoint with ID. Only users with role teacher can update the link")
    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    public LinkResponse update(@PathVariable Long id, @RequestBody LinkRequest linkRequest) {
        return linkService.update(id, linkRequest);

    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('INSTRUCTOR')")
    @Operation(summary = "Delete the link",
            description = "Delete links with ID. Only users with role teacher can delete links")
    public ResponseEntity<Map<String,Long>> delete(@PathVariable Long id) {
        Map<String,Long> response = new HashMap<>();
        response.put("id",linkService.delete(id));
       return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
