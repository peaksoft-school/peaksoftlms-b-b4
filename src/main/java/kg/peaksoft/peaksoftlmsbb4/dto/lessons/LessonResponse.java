package kg.peaksoft.peaksoftlmsbb4.dto.lessons;

import kg.peaksoft.peaksoftlmsbb4.dto.link.LinkResponse;
import kg.peaksoft.peaksoftlmsbb4.dto.presentation.PresentationResponse;
import kg.peaksoft.peaksoftlmsbb4.dto.task.TaskResponse;
import kg.peaksoft.peaksoftlmsbb4.dto.videoleson.VideoLessonResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LessonResponse {
    private Long id;
    private String name;
    private TaskResponse taskResponse;
    private LinkResponse linkResponse;
    private VideoLessonResponse videoLessonResponse;
    private PresentationResponse presentationResponse;

}
