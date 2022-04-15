package kg.peaksoft.peaksoftlmsbb4.api;

import kg.peaksoft.peaksoftlmsbb4.dto.AdminRequest;
import kg.peaksoft.peaksoftlmsbb4.dto.AdminResponse;
import kg.peaksoft.peaksoftlmsbb4.service.impl.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/admin")
@AllArgsConstructor
public class AdminApi {

    private final AdminService adminService;

    @PostMapping("/saveAdmin")
    public AdminResponse save(@Valid @RequestBody AdminRequest adminRequest) {
        return adminService.register(adminRequest);
    }

}
