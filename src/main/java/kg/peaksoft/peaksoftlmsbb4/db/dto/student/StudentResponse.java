package kg.peaksoft.peaksoftlmsbb4.db.dto.student;

import kg.peaksoft.peaksoftlmsbb4.db.enums.Role;
import kg.peaksoft.peaksoftlmsbb4.db.enums.StudyFormat;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentResponse {
    private Long id;
    private String studentName;
    private String lastName;
    private StudyFormat studyFormat;
    private String phoneNumber;
    private String email;
    private Role role;
    private String fullName;
    private String groupName;
}
