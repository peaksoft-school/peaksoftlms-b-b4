package kg.peaksoft.peaksoftlmsbb4.db.dto.student;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AssignStudentRequest {
    @JsonProperty("course_id")
    private Long courseId;
}
