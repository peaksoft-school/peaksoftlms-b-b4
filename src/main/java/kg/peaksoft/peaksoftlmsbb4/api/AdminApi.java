package kg.peaksoft.peaksoftlmsbb4.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.peaksoftlmsbb4.dto.AdminRequest;
import kg.peaksoft.peaksoftlmsbb4.dto.AdminResponse;
import kg.peaksoft.peaksoftlmsbb4.service.impl.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/admin")
@AllArgsConstructor
@CrossOrigin(origins = "http//localhost:1234", maxAge = 3600)
@Tag(name = "Admin", description = "The Admin API (register admin)")
public class AdminApi {

    private final AdminService adminService;

    @Operation(summary =
            "save registered admin")
    @PostMapping("/saveAdmin")
    public AdminResponse save(@Valid @RequestBody AdminRequest adminRequest) {
        return adminService.register(adminRequest);
    }
}
