package kg.peaksoft.peaksoftlmsbb4.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.peaksoftlmsbb4.controller.payload.request.VideoLessonRequest;
import kg.peaksoft.peaksoftlmsbb4.controller.payload.response.VideoLessonResponse;
import kg.peaksoft.peaksoftlmsbb4.db.service.VideoLessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/video-lessons")
@PreAuthorize("hasAnyAuthority('INSTRUCTOR')")
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "Video Lessons API", description = "The Video Lessons endpoints")
public class VideoLessonApi {

    private final VideoLessonService videoLessonService;


    @Operation(summary = "Add new video lessons", description = "This method save new video lessons.Only users with role teacher can add new video to lesson")
    @PostMapping
    public VideoLessonResponse saveVideo(@RequestBody VideoLessonRequest videoLessonRequest) {
        return videoLessonService.saveVideoLessons(videoLessonRequest);
    }


    @Operation(summary = "Gets a single videos by identifier", description = "For valid response try integer IDs with value >= 1 and...")
    @PreAuthorize("hasAnyAuthority('STUDENT')")
    @GetMapping("{id}")
    public VideoLessonResponse findById(@PathVariable Long id) {
        return videoLessonService.findById(id);
    }


    @Operation(summary = "Update the video_lessons", description = "Updates the details of an endpoint with ID. Only users with role teacher can add new video to lesson")
    @PutMapping("{id}")
    public VideoLessonResponse update(@PathVariable Long id, @RequestBody VideoLessonRequest videoLessonRequest) {
        return videoLessonService.update(id, videoLessonRequest);
    }

    @Operation(summary = "Delete the video lesson", description = "Delete the video lesson with ID")
    @DeleteMapping("{id}")
    public VideoLessonResponse delete(@PathVariable Long id) {
        return videoLessonService.delete(id);
    }


    @Operation(summary = "Gets a single videos by lesson identifier", description = "For valid response try integer IDs with value >= 1 and...")
    @GetMapping("lesson/{id}")
    public VideoLessonResponse getVideoByLessonId(@PathVariable Long id) {
        return videoLessonService.findLessonByLessonId(id);
    }
}
