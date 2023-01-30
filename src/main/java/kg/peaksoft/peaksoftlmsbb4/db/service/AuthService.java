package kg.peaksoft.peaksoftlmsbb4.db.service;

import kg.peaksoft.peaksoftlmsbb4.controller.payload.authentification.AuthRequest;
import kg.peaksoft.peaksoftlmsbb4.controller.payload.authentification.AuthResponseDto;

public interface AuthService {
    AuthResponseDto authenticate(AuthRequest authRequest);
}
