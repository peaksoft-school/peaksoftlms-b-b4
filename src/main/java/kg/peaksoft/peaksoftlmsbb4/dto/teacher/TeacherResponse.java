package kg.peaksoft.peaksoftlmsbb4.dto.teacher;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TeacherResponse {

    private Long id;

    @JsonProperty("teacher_name")
    private String teacherName;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("phone_number")
    private String phoneNumber;

    private String specialization;

    private String email;

}
