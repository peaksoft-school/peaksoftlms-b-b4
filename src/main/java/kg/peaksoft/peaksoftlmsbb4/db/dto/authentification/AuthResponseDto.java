package kg.peaksoft.peaksoftlmsbb4.db.dto.authentification;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AuthResponseDto {
    private String email;
    private String token;
}
