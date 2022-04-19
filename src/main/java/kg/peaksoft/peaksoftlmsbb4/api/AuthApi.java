package kg.peaksoft.peaksoftlmsbb4.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.peaksoftlmsbb4.dto.AuthRequestDto;
import kg.peaksoft.peaksoftlmsbb4.dto.AuthResponseDto;
import kg.peaksoft.peaksoftlmsbb4.service.impl.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;

@RestController
@AllArgsConstructor
@RequestMapping("api/authentication")
@CrossOrigin(origins = "http//localhost:1234", maxAge = 3600)
@Tag(name = "AuthApi", description = "The AuthApi (for authentication) ")
public class AuthApi {

    private final AuthService authService;

    @Operation(summary = "Retrieve Authentication Token",
            description = "This entrypoint returns a JWT auth_token for authenticating further requests to the API")

    @PostMapping("/login")
    @PermitAll
    private AuthResponseDto authentication(@RequestBody AuthRequestDto authRequestDto) {
        return authService.authenticate(authRequestDto);
    }
}
