package kg.peaksoft.peaksoftlmsbb4.controller.payload.authentification;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthRequest {
    private String email;
    private String password;
}
