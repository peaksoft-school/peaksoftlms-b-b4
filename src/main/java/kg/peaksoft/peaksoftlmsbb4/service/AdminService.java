package kg.peaksoft.peaksoftlmsbb4.service;

import kg.peaksoft.peaksoftlmsbb4.dto.AdminRequest;
import kg.peaksoft.peaksoftlmsbb4.dto.AdminResponse;

public interface AdminService {
    AdminResponse saveAdmin(AdminRequest adminRequest);
    void deleteById(Long id);
}
