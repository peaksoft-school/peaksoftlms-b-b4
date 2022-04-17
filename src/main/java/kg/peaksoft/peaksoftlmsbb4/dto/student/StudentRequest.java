package kg.peaksoft.peaksoftlmsbb4.dto.student;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kg.peaksoft.peaksoftlmsbb4.annotations.phoneNumber.ValidPhoneNumber;
import kg.peaksoft.peaksoftlmsbb4.enums.Role;
import kg.peaksoft.peaksoftlmsbb4.enums.StudyFormat;
import kg.peaksoft.peaksoftlmsbb4.model.Course;
import kg.peaksoft.peaksoftlmsbb4.model.Group;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Transient;
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

    @ValidPhoneNumber
    private String phoneNumber;

    private StudyFormat studyFormat;

    @Email(message = "email must follow the formatter :***@**")
    @NotEmpty(message = "email must have a value")
    private String email;
    @JsonIgnore
    private Group group;
    private Long  groupId;




}
