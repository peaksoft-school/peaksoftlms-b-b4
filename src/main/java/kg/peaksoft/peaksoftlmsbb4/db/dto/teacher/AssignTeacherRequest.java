package kg.peaksoft.peaksoftlmsbb4.db.dto.teacher;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AssignTeacherRequest {
    private Long courseId;
    private List<Long> teacherId;
}
