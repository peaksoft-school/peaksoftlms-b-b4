package kg.peaksoft.peaksoftlmsbb4.db.service;

import kg.peaksoft.peaksoftlmsbb4.controller.payload.request.AuthRequest;
import kg.peaksoft.peaksoftlmsbb4.controller.payload.response.AuthResponse;

public interface AuthService {

    AuthResponse authenticate(AuthRequest authRequest);

}
