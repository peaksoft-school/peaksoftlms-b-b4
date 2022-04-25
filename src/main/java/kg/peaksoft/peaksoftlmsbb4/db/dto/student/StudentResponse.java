package kg.peaksoft.peaksoftlmsbb4.db.dto.student;

import com.fasterxml.jackson.annotation.JsonProperty;
import kg.peaksoft.peaksoftlmsbb4.db.enums.StudyFormat;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentResponse {
    private Long id;
    @JsonProperty("student_name")
    private String studentName;
    @JsonProperty("last_name")
    private String lastName;
    @JsonProperty("study_format")
    private StudyFormat studyFormat;
    @JsonProperty("phone_number")
    private String phoneNumber;
    private String email;

}
