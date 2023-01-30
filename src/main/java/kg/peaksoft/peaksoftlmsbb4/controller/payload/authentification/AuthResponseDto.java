package kg.peaksoft.peaksoftlmsbb4.controller.payload.authentification;

import kg.peaksoft.peaksoftlmsbb4.db.enums.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AuthResponseDto {
    private String email;
    private String token;
    private Role role;
}
