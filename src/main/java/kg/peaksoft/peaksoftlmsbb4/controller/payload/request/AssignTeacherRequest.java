package kg.peaksoft.peaksoftlmsbb4.controller.payload.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AssignTeacherRequest {

    private Long courseId;
    private List<Long> teacherId;

}
