package kg.peaksoft.peaksoftlmsbb4.api;

import kg.peaksoft.peaksoftlmsbb4.dto.AdminRequest;
import kg.peaksoft.peaksoftlmsbb4.dto.AdminResponse;
import kg.peaksoft.peaksoftlmsbb4.enums.Role;
import kg.peaksoft.peaksoftlmsbb4.exception.BadRequestException;
import kg.peaksoft.peaksoftlmsbb4.model.Admin;
import kg.peaksoft.peaksoftlmsbb4.model.User;
import kg.peaksoft.peaksoftlmsbb4.repository.AdminRepository;
import kg.peaksoft.peaksoftlmsbb4.service.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.security.PermitAll;

@AllArgsConstructor
@RestController
@RequestMapping("api/admin")
public class AdminApi {
    private final AdminService adminService;
    private final PasswordEncoder passwordEncoder;
    private final AdminRepository adminRepository;

    @PostMapping("/save")
    @PermitAll
    private AdminResponse saveAdmin(@RequestBody AdminRequest adminRequest){
        return adminService.saveAdmin(adminRequest);
    }

}
