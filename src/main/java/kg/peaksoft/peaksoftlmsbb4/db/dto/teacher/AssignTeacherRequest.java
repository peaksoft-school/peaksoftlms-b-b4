package kg.peaksoft.peaksoftlmsbb4.db.dto.teacher;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class AssignTeacherRequest {
    @JsonProperty("course_id")
    private Long courseId;
    @JsonProperty("teacher_id")
    private List<Long> teacherId;
}
