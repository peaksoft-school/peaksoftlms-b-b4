package kg.peaksoft.peaksoftlmsbb4.db.service;

import kg.peaksoft.peaksoftlmsbb4.controller.payload.authentification.AuthRequestDto;
import kg.peaksoft.peaksoftlmsbb4.controller.payload.authentification.AuthResponseDto;

public interface AuthService {
    AuthResponseDto authenticate(AuthRequestDto authRequestDto);
}
