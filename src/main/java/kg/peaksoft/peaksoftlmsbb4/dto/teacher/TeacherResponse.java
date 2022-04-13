package kg.peaksoft.peaksoftlmsbb4.dto.teacher;

import kg.peaksoft.peaksoftlmsbb4.enums.Role;
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

    private String password;

    private Role role;
}
