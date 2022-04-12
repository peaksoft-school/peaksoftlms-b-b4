package kg.peaksoft.peaksoftlmsbb4.api;

import kg.peaksoft.peaksoftlmsbb4.dto.AuthRequestDto;
import kg.peaksoft.peaksoftlmsbb4.dto.AuthResponseDto;
import kg.peaksoft.peaksoftlmsbb4.service.impl.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.PermitAll;

@RestController
@AllArgsConstructor
@RequestMapping("api/authentication")
public class AuthApi {
    private final AuthService authService;

    @PermitAll
    @PostMapping("/token")
    private AuthResponseDto authentication(@RequestBody AuthRequestDto authRequestDto) {
        return authService.authenticate(authRequestDto);
    }
}
