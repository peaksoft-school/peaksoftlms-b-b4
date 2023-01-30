package kg.peaksoft.peaksoftlmsbb4.controller.payload.videolesson;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VideoLessonResponse {
    private Long id;
    private String name;
    private String description;
    private String link;

}
