package kg.peaksoft.peaksoftlmsbb4.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.peaksoftlmsbb4.dto.lessons.LessonRequest;
import kg.peaksoft.peaksoftlmsbb4.dto.lessons.LessonResponse;
import kg.peaksoft.peaksoftlmsbb4.service.LessonService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/lessons")
@Tag(name = "Lessons", description = "The Lessons API")
@CrossOrigin(origins = "http//localhost:1234", maxAge = 3600)
public class LessonsApi {
    private final LessonService lessonService;

    @PostMapping("{id}")
    @Operation(summary = "Add new lesson", description = "This method save new lessons")
    @PreAuthorize("hasAnyAuthority('ADMIN','TEACHER')")
    public LessonResponse saveLesson(@PathVariable Long id,@RequestBody LessonRequest lessonRequest) {
        return lessonService.saveLessons(id,lessonRequest);
    }

    @GetMapping("/{id}")
    @Operation(summary = "gets a single lessons by identifier")
    @PreAuthorize("hasAnyAuthority('ADMIN','TEACHER')")
    public LessonResponse findById(@PathVariable Long id) {
        return lessonService.findById(id);
    }

    @GetMapping
    @Operation(summary = "gets a list", description = "Returns all lessons that are,if there are no lessons,then an error")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Found the lessons",
                    content = {
                            @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = LessonsApi.class)))})})
    @PreAuthorize("hasAnyAuthority('ADMIN','TEACHER')")
    public List<LessonResponse> findAll() {
        return lessonService.findAll();
    }

    @PutMapping("/{id}")
    @Operation(summary = "update the LESSONS", description = "Updates the details of an endpoint with ID")
    @PreAuthorize("hasAnyAuthority('ADMIN','TEACHER')")
    public LessonResponse update(@PathVariable Long id, @RequestBody LessonRequest lessonRequest) {
        return lessonService.update(id, lessonRequest);

    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','TEACHER')")
    @Operation(summary = "delete lessons by id")
    public void delete(@PathVariable Long id) {
        lessonService.delete(id);
    }

}
