package kg.peaksoft.peaksoftlmsbb4.controller.payload.request;

import kg.peaksoft.peaksoftlmsbb4.annotations.phoneNumber.ValidPhoneNumber;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Setter
@Getter
public class TeacherRequest {

    @NotBlank
    @Size(min = 2, max = 20, message = "first name should be between 2 and 20 characters")
    private String teacherName;

    @NotBlank
    @Size(min = 2, max = 20, message = "last name should be between 2 and 20 characters")
    private String lastName;

    @ValidPhoneNumber
    private String phoneNumber;

    @NotBlank(message = "specialization must have a value")
    private String specialization;

    @Email
    @NotEmpty(message = "email must have a value")
    private String email;

    private String password;

}
