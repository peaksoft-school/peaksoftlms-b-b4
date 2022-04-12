package kg.peaksoft.peaksoftlmsbb4.mapper;

import kg.peaksoft.peaksoftlmsbb4.converter.Converter;
import kg.peaksoft.peaksoftlmsbb4.dto.AdminRequest;
import kg.peaksoft.peaksoftlmsbb4.dto.AdminResponse;
import kg.peaksoft.peaksoftlmsbb4.model.Admin;
import kg.peaksoft.peaksoftlmsbb4.model.User;
import org.springframework.stereotype.Component;

@Component
public class AdminMapper implements Converter<Admin, AdminRequest, AdminResponse> {

    @Override
    public Admin convert(AdminRequest request) {
        Admin admin =new Admin();


        admin.setFirstName(request.getFirstName());
        admin.setLastName(request.getLastName());

        User user=new User();
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());

        admin.setUser(user);
        return admin;
    }

    @Override
    public AdminResponse deConvert(Admin admin) {
        AdminResponse adminResponse=new AdminResponse();
        adminResponse.setId(admin.getId());
        adminResponse.setFirstName(admin.getFirstName());
        adminResponse.setLastName(admin.getLastName());

        adminResponse.setEmail(admin.getUser().getEmail());
        adminResponse.setPassword(admin.getUser().getPassword());
        return adminResponse;
    }
}
