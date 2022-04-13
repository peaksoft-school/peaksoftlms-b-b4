package kg.peaksoft.peaksoftlmsbb4.dto.student;

import kg.peaksoft.peaksoftlmsbb4.enums.StudyFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
@Getter
@Setter
public class StudentRequest {
    @NotBlank
    @Size(min = 2, max = 20, message = "first name should be between 2 and 20 characters")
    private String studentName;
    @NotBlank
    @Size(min = 2, max = 20, message = "last name should be between 2 and 20 characters")
    private String lastName;
    private String phoneNumber;
    @Enumerated(value = EnumType.STRING)
    @NotBlank
    private StudyFormat studyFormat;
    @Email(message = "email must follow the formatter :***@**")
    @NotEmpty(message = "email must have a value")
    private String email;
    @NotBlank
    private String password;
}
