package kg.peaksoft.peaksoftlmsbb4.db.service;

import kg.peaksoft.peaksoftlmsbb4.db.dto.authentification.AuthRequestDto;
import kg.peaksoft.peaksoftlmsbb4.db.dto.authentification.AuthResponseDto;

public interface AuthService {
    AuthResponseDto authenticate(AuthRequestDto authRequestDto);
}
