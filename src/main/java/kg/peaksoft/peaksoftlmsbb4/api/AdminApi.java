package kg.peaksoft.peaksoftlmsbb4.api;

import kg.peaksoft.peaksoftlmsbb4.dto.AdminRequest;
import kg.peaksoft.peaksoftlmsbb4.dto.AdminResponse;
import kg.peaksoft.peaksoftlmsbb4.service.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("api/admin")
public class AdminApi {
    private final AdminService adminService;

    @PostMapping("/save")
    private AdminResponse saveAdmin(@RequestBody AdminRequest adminRequest){
        return adminService.saveAdmin(adminRequest);
    }
}
