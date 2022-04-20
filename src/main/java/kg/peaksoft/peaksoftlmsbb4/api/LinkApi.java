package kg.peaksoft.peaksoftlmsbb4.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.peaksoftlmsbb4.dto.link.LinkRequest;
import kg.peaksoft.peaksoftlmsbb4.dto.link.LinkResponse;
import kg.peaksoft.peaksoftlmsbb4.model.Link;
import kg.peaksoft.peaksoftlmsbb4.service.LinkService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/links")
@Tag(name = "Links", description = "The Links API")
@CrossOrigin(origins = "http//localhost:1234", maxAge = 3600)
public class LinkApi {
    private final LinkService linkService;

    @PostMapping("/{id}")
    @Operation(summary = "Add new link", description = "This method save new links")
    @PreAuthorize("hasAnyAuthority('ADMIN','TEACHER')")
    public LinkResponse saveLinks(@RequestBody LinkRequest linkRequest,@PathVariable Long id) {
        return linkService.saveLinks(id,linkRequest);
    }

    @GetMapping("/{id}")
    @Operation(summary = "gets a single links by identifier")
    @PreAuthorize("hasAnyAuthority('ADMIN','TEACHER')")
    public Link findById(@PathVariable Long id) {
        return linkService.findById(id);
    }

    @GetMapping
    @Operation(summary = "gets a list", description = "Returns all links that are,if there are no links,then an error")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Found the links",
                    content = {
                            @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = LinkApi.class)))})})
    @PreAuthorize("hasAnyAuthority('ADMIN','TEACHER')")
    public List<LinkResponse> findAll() {
        return linkService.findAll();
    }

    @PutMapping("/{id}")
    @Operation(summary = "update the Links", description = "Updates the details of an endpoint with ID")
    @PreAuthorize("hasAnyAuthority('ADMIN','TEACHER')")
    public LinkResponse update(@PathVariable Long id, @RequestBody LinkRequest linkRequest) {
        return linkService.update(id, linkRequest);

    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','TEACHER')")
    @Operation(summary = "delete links by id")
    public void delete(@PathVariable Long id) {
        linkService.delete(id);
    }
}
