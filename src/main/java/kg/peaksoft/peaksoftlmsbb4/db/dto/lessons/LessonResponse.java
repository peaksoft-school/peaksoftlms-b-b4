package kg.peaksoft.peaksoftlmsbb4.db.dto.lessons;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class LessonResponse {
    private Long id;
    private String name;
    private List<Long> taskId;
    private List<Long> linkId;
    private List<Long> videoLessonId;
    private List<Long> presentationId;

}
