package kg.peaksoft.peaksoftlmsbb4.controller.payload.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TaskResponse {
    private Long id;
    private String name;
    private List<ResourceResponse> resources;
}
