package kg.peaksoft.peaksoftlmsbb4.dto;

import kg.peaksoft.peaksoftlmsbb4.enums.StudyFormat;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentResponse {
    private String id;
    private String studentName;
    private StudyFormat studyFormat;
    private String phoneNumber;
    private String lastName;
    private String email;
    private String password;

}
