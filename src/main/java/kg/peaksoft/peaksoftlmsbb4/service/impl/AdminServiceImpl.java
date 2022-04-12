package kg.peaksoft.peaksoftlmsbb4.service.impl;

import kg.peaksoft.peaksoftlmsbb4.dto.AdminRequest;
import kg.peaksoft.peaksoftlmsbb4.dto.AdminResponse;
import kg.peaksoft.peaksoftlmsbb4.exception.BadRequestException;
import kg.peaksoft.peaksoftlmsbb4.mapper.AdminMapper;
import kg.peaksoft.peaksoftlmsbb4.model.Admin;
import kg.peaksoft.peaksoftlmsbb4.repository.AdminRepository;
import kg.peaksoft.peaksoftlmsbb4.service.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AdminServiceImpl  implements AdminService {
    private final AdminRepository adminRepository;
    private final AdminMapper adminMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AdminResponse saveAdmin(AdminRequest adminRequest) {
        String email = adminRequest.getEmail();
        boolean exists = adminRepository.existsByUser_Email(email);
        if (exists) {
            throw new BadRequestException(
                    String.format("admin with email = %s has already exists", email)
            );
        }
        String encoderPassword = passwordEncoder.encode(adminRequest.getPassword());
        adminRequest.setPassword(encoderPassword);
        Admin admin= adminMapper.convert(adminRequest);
        Admin save = adminRepository.save(admin);
        return adminMapper.deConvert(save);
    }
}
