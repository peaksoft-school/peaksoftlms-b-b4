package kg.peaksoft.peaksoftlmsbb4.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.peaksoftlmsbb4.db.dto.videolesson.VideoLessonRequest;
import kg.peaksoft.peaksoftlmsbb4.db.dto.videolesson.VideoLessonResponse;
import kg.peaksoft.peaksoftlmsbb4.db.service.VideoLessonService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("api/videoLessons")
@Tag(name = "VideoLessons", description = "The Video_Lessons API")
@CrossOrigin(origins = "*", maxAge = 3600)
public class VideoLessonApi {

    private final VideoLessonService videoLessonService;

    @PostMapping
    @Operation(summary = "Add new video lessons",
            description = "This method save new video lessons.Only users with role teacher can add new video to lesson")
    @PreAuthorize("hasAnyAuthority('INSTRUCTOR')")
    public VideoLessonResponse saveVideo(@RequestBody VideoLessonRequest videoLessonRequest) {
        return videoLessonService.saveVideoLessons(videoLessonRequest);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Gets a single videos by identifier",
            description = "For valid response try integer IDs with value >= 1 and...")
    @PreAuthorize("hasAnyAuthority('INSTRUCTOR')")
    public VideoLessonResponse findById(@PathVariable Long id) {
        return videoLessonService.findById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update the video_lessons",
            description = "Updates the details of an endpoint with ID. Only users with role teacher can add new video to lesson")
    @PreAuthorize("hasAnyAuthority('INSTRUCTOR')")
    public VideoLessonResponse update(@PathVariable Long id, @RequestBody VideoLessonRequest videoLessonRequest) {
        return videoLessonService.update(id, videoLessonRequest);
    }

    @Operation(summary = "Delete the video lesson",
            description = "Delete the video lesson with ID")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('INSTRUCTOR')")
    public ResponseEntity<Map<String,Long>> delete(@PathVariable Long id) {
        Map<String,Long> response = new HashMap<>();
        response.put("id",videoLessonService.delete(id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
