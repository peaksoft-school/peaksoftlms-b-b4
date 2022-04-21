package kg.peaksoft.peaksoftlmsbb4.dto.lessons;

import kg.peaksoft.peaksoftlmsbb4.dto.link.LinkResponse;
import kg.peaksoft.peaksoftlmsbb4.dto.presentation.PresentationResponse;
import kg.peaksoft.peaksoftlmsbb4.dto.task.TaskResponse;
import kg.peaksoft.peaksoftlmsbb4.dto.videoleson.VideoLessonResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class LessonResponse {
    private Long id;
    private String name;
    private List<TaskResponse> taskResponse;
    private List<LinkResponse> linkResponse;
    private List<VideoLessonResponse> videoLessonResponse;
    private List<PresentationResponse> presentationResponse;

}
