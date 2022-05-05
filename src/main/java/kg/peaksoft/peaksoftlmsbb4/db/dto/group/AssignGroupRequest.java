package kg.peaksoft.peaksoftlmsbb4.db.dto.group;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AssignGroupRequest {
    @JsonProperty("course_id")
    private Long courseId;
    @JsonProperty("group_id")
    private Long groupId;
}
