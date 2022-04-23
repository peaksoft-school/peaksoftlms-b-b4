package kg.peaksoft.peaksoftlmsbb4.mapper.admin;

import kg.peaksoft.peaksoftlmsbb4.converter.Converter;
import kg.peaksoft.peaksoftlmsbb4.db.dto.admin.AdminRequest;
import kg.peaksoft.peaksoftlmsbb4.db.dto.admin.AdminResponse;
import kg.peaksoft.peaksoftlmsbb4.db.enums.Role;
import kg.peaksoft.peaksoftlmsbb4.db.model.Admin;
import kg.peaksoft.peaksoftlmsbb4.db.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AdminMapper implements Converter<Admin, AdminRequest, AdminResponse> {
    @Override
    public Admin convert(AdminRequest request) {
        Admin admin = new Admin();
        admin.setFirstName(request.getFirstName());
        admin.setLastName(request.getLastName());
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setRole(Role.ADMIN);
        admin.setUser(user);
        return admin;
    }

    @Override
    public AdminResponse deConvert(Admin admin) {
        AdminResponse adminResponse = new AdminResponse();
        adminResponse.setId(admin.getId());
        adminResponse.setFirstName(admin.getFirstName());
        adminResponse.setLastName(admin.getLastName());

        adminResponse.setEmail(admin.getUser().getEmail());
        return adminResponse;
    }
}
