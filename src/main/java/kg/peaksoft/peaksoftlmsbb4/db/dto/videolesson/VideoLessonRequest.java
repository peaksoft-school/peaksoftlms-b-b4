package kg.peaksoft.peaksoftlmsbb4.db.dto.videolesson;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class VideoLessonRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @NotBlank
    private String link;
    private Long lessonId;

}
