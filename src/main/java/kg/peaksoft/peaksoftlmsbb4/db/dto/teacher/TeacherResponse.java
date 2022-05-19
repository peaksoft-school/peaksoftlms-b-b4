package kg.peaksoft.peaksoftlmsbb4.db.dto.teacher;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TeacherResponse {

    private Long id;

    private String teacherName;

    private String lastName;

    private String phoneNumber;

    private String specialization;

    private String email;

    private String fullName;

}
