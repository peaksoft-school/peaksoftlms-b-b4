package kg.peaksoft.peaksoftlmsbb4.db.dto.task;

import kg.peaksoft.peaksoftlmsbb4.db.dto.resource.ResourceResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TaskResponse {
   private String name;
   private List<ResourceResponse> resources;
}
