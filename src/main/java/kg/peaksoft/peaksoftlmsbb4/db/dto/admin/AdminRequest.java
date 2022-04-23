package kg.peaksoft.peaksoftlmsbb4.db.dto.admin;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Setter
@Getter
public class AdminRequest {

    @NotBlank
    @Size(min = 2, max = 20, message = "first name should be between 2 and 20 characters")
    @JsonProperty("first_name")
    private String firstName;

    @NotBlank
    @Size(min = 2, max = 20, message = "last name should be between 2 and 20 characters")
    @JsonProperty("last_name")
    private String lastName;

    @NotBlank
    @Email(message = "email was be")
    private String email;

    private String password;

}
