package kg.peaksoft.peaksoftlmsbb4.dto;

import kg.peaksoft.peaksoftlmsbb4.enums.StudyFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentResponce {
    private String id;
    private String studentName;
    private StudyFormat studyFormat;
    private String phoneNumber;
    private String email;
    private String password;


}
