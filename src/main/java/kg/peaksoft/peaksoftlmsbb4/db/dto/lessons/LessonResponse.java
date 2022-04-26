package kg.peaksoft.peaksoftlmsbb4.db.dto.lessons;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class LessonResponse {
    private Long id;
    private String name;
    @JsonProperty("task_id")
    private List<Long> taskId;
    @JsonProperty("link_id")
    private List<Long> linkId;
    @JsonProperty("video_lesson_id")
    private List<Long> videoLessonId;
    @JsonProperty("presentation_id")
    private List<Long> presentationId;

}
