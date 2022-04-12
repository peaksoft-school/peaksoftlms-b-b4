package kg.peaksoft.peaksoftlmsbb4.service.impl;

import kg.peaksoft.peaksoftlmsbb4.dto1.AuthRequestDto;
import kg.peaksoft.peaksoftlmsbb4.dto1.AuthResponseDto;
import kg.peaksoft.peaksoftlmsbb4.jwt.JwtUtils;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    public AuthResponseDto authenticate(AuthRequestDto authRequest) {
        Authentication authentication;

        authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authRequest.getEmail(),
                authRequest.getPassword()
        ));
        String generatedToken = jwtUtils.generateToken(authentication);

        return AuthResponseDto.builder()
                .email(authRequest.getEmail())
                .token(generatedToken)
                .build();
    }
}
