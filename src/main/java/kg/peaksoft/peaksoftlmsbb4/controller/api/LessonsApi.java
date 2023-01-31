package kg.peaksoft.peaksoftlmsbb4.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.peaksoftlmsbb4.controller.payload.request.LessonRequest;
import kg.peaksoft.peaksoftlmsbb4.controller.payload.response.LessonResponse;
import kg.peaksoft.peaksoftlmsbb4.db.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/lessons")
@PreAuthorize("hasAnyAuthority('INSTRUCTOR')")
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "Lesson API", description = "The Lesson endpoints")
public class LessonsApi {

    private final LessonService lessonService;

    @PostMapping
    @Operation(summary = "Add new lesson to course",
            description = "This endpoint save new lesson to course by ID. Only users with role teacher can add new lessons")
    public LessonResponse saveLesson(@RequestBody LessonRequest lessonRequest) {
        return lessonService.saveLessons(lessonRequest);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Gets a single lessons by identifier",
            description = "For valid response try integer IDs with value >= 1 and...")
    @PreAuthorize("hasAnyAuthority('STUDENT')")
    public LessonResponse findById(@PathVariable Long id) {
        return lessonService.findById(id);
    }

    @GetMapping("courseLessons/{id}")
    @Operation(summary = "Gets a list",
            description = "Returns all course's lessons that are,if there are no lessons,then an error")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Found the lessons",
                    content = {
                            @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = LessonsApi.class)))})})
    @PreAuthorize("hasAnyAuthority('STUDENT')")
    public List<LessonResponse> findAll(@PathVariable Long id) {
        return lessonService.findAll(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update the lessons",
            description = "Updates the details of an endpoint with ID. Only users with role teacher can update the lesson")

    public LessonResponse update(@PathVariable Long id, @RequestBody LessonRequest lessonRequest) {
        return lessonService.update(id, lessonRequest);

    }

    @DeleteMapping("/{id}")

    @Operation(summary = "Delete the lesson",
            description = "Delete lesson with ID. Only users with role teacher can delete lesson")
    public LessonResponse delete(@PathVariable Long id) {
        return lessonService.delete(id);
    }

}
