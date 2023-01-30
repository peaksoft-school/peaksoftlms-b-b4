package kg.peaksoft.peaksoftlmsbb4.controller.payload.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TaskRequest {
    private String name;
    private List<ResourceRequest> resources;
    private Long lessonId;
}
