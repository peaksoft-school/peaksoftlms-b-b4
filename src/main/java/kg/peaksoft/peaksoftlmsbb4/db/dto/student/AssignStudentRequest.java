package kg.peaksoft.peaksoftlmsbb4.db.dto.student;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AssignStudentRequest {
    private Long courseId;
    private Long studentId;
}
