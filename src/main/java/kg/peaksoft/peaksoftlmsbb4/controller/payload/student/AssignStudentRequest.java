package kg.peaksoft.peaksoftlmsbb4.controller.payload.student;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AssignStudentRequest {
    private Long courseId;
    private Long studentId;
}
