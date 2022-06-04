package kg.peaksoft.peaksoftlmsbb4.db.dto.lessons;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LessonResponse {
    private Long id;
    private String name;
    private Long videoLessonId;
    private Long presentationId;
    private Long linkId;
    private Long taskId;
    private Long testId;
}
