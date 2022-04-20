package kg.peaksoft.peaksoftlmsbb4.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.peaksoftlmsbb4.dto.videoleson.VideoLessonRequest;
import kg.peaksoft.peaksoftlmsbb4.dto.videoleson.VideoLessonResponse;
import kg.peaksoft.peaksoftlmsbb4.model.VideoLesson;
import kg.peaksoft.peaksoftlmsbb4.service.VideoLessonService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/videoLessons")
@Tag(name = "VideoLessons", description = "The Video_Lessons API")
@CrossOrigin(origins = "http//localhost:1234", maxAge = 3600)
public class VideoLessonApi {
    private final VideoLessonService videoLessonService;

    @PostMapping("/{id}")
    @Operation(summary = "Add new video lessons", description = "This method save new video lessons")
    @PreAuthorize("hasAnyAuthority('ADMIN','TEACHER')")
    public VideoLessonResponse saveVideo(@RequestBody VideoLessonRequest videoLessonRequest,@PathVariable Long id) {
        return videoLessonService.saveVideoLessons(id,videoLessonRequest);
    }

    @GetMapping("/{id}")
    @Operation(summary = "gets a single videos by identifier")
    @PreAuthorize("hasAnyAuthority('ADMIN','TEACHER')")
    public VideoLesson findById(@PathVariable Long id) {
        return videoLessonService.findById(id);
    }

    @GetMapping
    @Operation(summary = "gets a list", description = "Returns all videos that are,if there are no videos,then an error")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Found the videos",
                    content = {
                            @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = VideoLessonApi.class)))})})
    @PreAuthorize("hasAnyAuthority('ADMIN','TEACHER')")
    public List<VideoLessonResponse> findAll() {
        return videoLessonService.findAll();

    }

    @PutMapping("/{id}")
    @Operation(summary = "update the video_lessons", description = "Updates the details of an endpoint with ID")
    @PreAuthorize("hasAnyAuthority('ADMIN','TEACHER')")
    public VideoLessonResponse update(@PathVariable Long id, @RequestBody VideoLessonRequest videoLessonRequest) {
        return videoLessonService.update(id, videoLessonRequest);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','TEACHER')")
    @Operation(summary = "delete videos by id")
    public void delete(@PathVariable Long id) {
        videoLessonService.delete(id);
    }
}
