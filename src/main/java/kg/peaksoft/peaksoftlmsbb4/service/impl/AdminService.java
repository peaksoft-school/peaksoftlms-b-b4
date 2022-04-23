package kg.peaksoft.peaksoftlmsbb4.service.impl;

import kg.peaksoft.peaksoftlmsbb4.db.dto.admin.AdminRequest;
import kg.peaksoft.peaksoftlmsbb4.db.dto.admin.AdminResponse;
import kg.peaksoft.peaksoftlmsbb4.exception.BadRequestException;
import kg.peaksoft.peaksoftlmsbb4.mapper.admin.AdminMapper;
import kg.peaksoft.peaksoftlmsbb4.db.model.Admin;
import kg.peaksoft.peaksoftlmsbb4.repository.AdminRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class AdminService {

    private final PasswordEncoder passwordEncoder;
    private final AdminRepository adminRepository;
    private final AdminMapper adminMapper;

    public AdminResponse register(AdminRequest adminRequest) {
        String email = adminRequest.getEmail();

        boolean exists = adminRepository.existsByUserEmail(email);

        if (exists) {
            throw new BadRequestException(
                    String.format("admin with email = %s has already exists", email)
            );
        }

        String encodedPassword = passwordEncoder.encode(adminRequest.getPassword());
        adminRequest.setPassword(encodedPassword);

        Admin admin = adminMapper.convert(adminRequest);

        Admin save = adminRepository.save(admin);

        return adminMapper.deConvert(save);
    }
}
