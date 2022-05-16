package kg.peaksoft.peaksoftlmsbb4.db.dto.task;

import com.fasterxml.jackson.annotation.JsonProperty;
import kg.peaksoft.peaksoftlmsbb4.db.dto.resource.ResourceRequest;
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
