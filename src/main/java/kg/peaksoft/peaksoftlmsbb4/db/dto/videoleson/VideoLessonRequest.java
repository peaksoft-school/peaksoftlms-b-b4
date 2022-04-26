package kg.peaksoft.peaksoftlmsbb4.db.dto.videoleson;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class VideoLessonRequest {
    private String name;
    private String description;
    @NotBlank
    private String link;
    @JsonProperty("lesson_id")
    private Long lessonId;

}
