package kg.peaksoft.peaksoftlmsbb4.controller.payload.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AssignGroupRequest {
    private Long courseId;
    private Long groupId;
}
