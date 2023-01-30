package kg.peaksoft.peaksoftlmsbb4.controller.payload.task;

import kg.peaksoft.peaksoftlmsbb4.controller.payload.resource.ResourceRequest;
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
