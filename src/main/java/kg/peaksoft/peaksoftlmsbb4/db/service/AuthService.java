package kg.peaksoft.peaksoftlmsbb4.db.service;

import kg.peaksoft.peaksoftlmsbb4.controller.payload.authentification.AuthRequest;
import kg.peaksoft.peaksoftlmsbb4.controller.payload.authentification.AuthResponse;

public interface AuthService {
    AuthResponse authenticate(AuthRequest authRequest);
}
