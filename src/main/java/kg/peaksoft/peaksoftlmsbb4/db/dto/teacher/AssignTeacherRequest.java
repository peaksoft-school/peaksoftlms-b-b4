package kg.peaksoft.peaksoftlmsbb4.db.dto.teacher;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AssignTeacherRequest {
    @JsonProperty("course_id")
    private Long courseId;
}
