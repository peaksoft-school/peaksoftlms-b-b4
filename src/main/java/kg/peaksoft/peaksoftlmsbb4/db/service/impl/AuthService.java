package kg.peaksoft.peaksoftlmsbb4.db.service.impl;

import kg.peaksoft.peaksoftlmsbb4.db.dto.authentification.AuthRequestDto;
import kg.peaksoft.peaksoftlmsbb4.db.dto.authentification.AuthResponseDto;
import kg.peaksoft.peaksoftlmsbb4.security.jwt.JwtUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@Slf4j
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
        log.info("successful generated token :{}",generatedToken);
        return AuthResponseDto.builder()
                .email(authRequest.getEmail())
                .token(generatedToken)
                .build();
    }
}
