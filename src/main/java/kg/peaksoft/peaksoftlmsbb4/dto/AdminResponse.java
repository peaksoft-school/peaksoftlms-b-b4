package kg.peaksoft.peaksoftlmsbb4.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminResponse {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;
}
