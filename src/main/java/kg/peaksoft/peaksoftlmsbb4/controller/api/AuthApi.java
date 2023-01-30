package kg.peaksoft.peaksoftlmsbb4.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.peaksoftlmsbb4.controller.payload.request.AuthRequest;
import kg.peaksoft.peaksoftlmsbb4.controller.payload.response.AuthResponse;
import kg.peaksoft.peaksoftlmsbb4.db.service.impl.AuthServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;

@PermitAll
@RestController
@AllArgsConstructor
@RequestMapping("api/authentication")
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "AuthApi", description = "The AuthApi (for authentication) ")
public class AuthApi {

    private final AuthServiceImpl authService;

    @Operation(summary = "Retrieve Authentication Token",
            description = "This entrypoint returns a JWT auth_token for authenticating further requests to the API")
    @PostMapping("/login")
    private AuthResponse authentication(@RequestBody AuthRequest authRequest) {
        return authService.authenticate(authRequest);
    }

}
