package kg.peaksoft.peaksoftlmsbb4.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.peaksoftlmsbb4.db.dto.videoleson.VideoLessonRequest;
import kg.peaksoft.peaksoftlmsbb4.db.dto.videoleson.VideoLessonResponse;
import kg.peaksoft.peaksoftlmsbb4.db.service.VideoLessonService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/videoLessons")
@Tag(name = "VideoLessons", description = "The Video_Lessons API")
@CrossOrigin(origins = "*", maxAge = 3600)
public class VideoLessonApi {
    private final VideoLessonService videoLessonService;

    @PostMapping("/{id}")
    @Operation(summary = "Add new video lessons",
            description = "This method save new video lessons.Only users with role teacher can add new video to lesson")
    @PreAuthorize("hasAnyAuthority('TEACHER')")
    public VideoLessonResponse saveVideo(@PathVariable Long id, @RequestBody VideoLessonRequest videoLessonRequest) {
        return videoLessonService.saveVideoLessons(id, videoLessonRequest);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Gets a single videos by identifier",
            description = "For valid response try integer IDs with value >= 1 and...")
    @PreAuthorize("hasAnyAuthority('TEACHER')")
    public VideoLessonResponse findById(@PathVariable Long id) {
        return videoLessonService.findById(id);
    }

    @GetMapping
    @Operation(summary = "Gets a list",
            description = "Returns all videos that are,if there are no videos,then an error")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Found the videos",
                    content = {
                            @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = VideoLessonApi.class)))})})
    @PreAuthorize("hasAnyAuthority('TEACHER')")
    public List<VideoLessonResponse> findAll() {
        return videoLessonService.findAll();

    }

    @PutMapping("/{id}")
    @Operation(summary = "Update the video_lessons",
            description = "Updates the details of an endpoint with ID. Only users with role teacher can add new video to lesson")
    @PreAuthorize("hasAnyAuthority('TEACHER')")
    public VideoLessonResponse update(@PathVariable Long id, @RequestBody VideoLessonRequest videoLessonRequest) {
        return videoLessonService.update(id, videoLessonRequest);
    }

    @Operation(summary = "Delete the video lesson",
            description = "Delete the video lesson with ID")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('TEACHER')")
    public void delete(@PathVariable Long id) {
        videoLessonService.delete(id);
    }
}
