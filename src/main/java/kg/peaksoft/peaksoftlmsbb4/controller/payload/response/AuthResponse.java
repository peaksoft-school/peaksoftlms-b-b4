package kg.peaksoft.peaksoftlmsbb4.controller.payload.response;

import kg.peaksoft.peaksoftlmsbb4.db.enums.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AuthResponse {

    private String email;
    private String token;
    private Role role;

}
