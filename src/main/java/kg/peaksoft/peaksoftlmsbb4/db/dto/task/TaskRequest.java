package kg.peaksoft.peaksoftlmsbb4.db.dto.task;

import com.fasterxml.jackson.annotation.JsonProperty;
import kg.peaksoft.peaksoftlmsbb4.db.dto.resource.ResourceRequest;
import kg.peaksoft.peaksoftlmsbb4.db.enums.ResourceType;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
public class TaskRequest {
    private String name;
    @JsonProperty("resource")
    private List<ResourceRequest> resourceRequests;
    @JsonProperty("lesson_id")
    private Long lessonId;
}
