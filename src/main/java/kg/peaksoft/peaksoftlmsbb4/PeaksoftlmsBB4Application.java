package kg.peaksoft.peaksoftlmsbb4;

import kg.peaksoft.peaksoftlmsbb4.enums.Role;
import kg.peaksoft.peaksoftlmsbb4.model.Admin;
import kg.peaksoft.peaksoftlmsbb4.model.User;
import kg.peaksoft.peaksoftlmsbb4.repository.AdminRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;

@SpringBootApplication
@AllArgsConstructor
public class PeaksoftlmsBB4Application {
     private final AdminRepository adminRepository;
     private final PasswordEncoder passwordEncoder;
    public static void main(String[] args) {
        SpringApplication.run(PeaksoftlmsBB4Application.class, args);
        System.out.println("Welcome colleagues, project name is Peaksoftlms-B");

    }
    @PostConstruct
    private void init(){
        Admin admin=new Admin();
        admin.setFirstName("Rahim");
        admin.setLastName("Kurbanov");
        User user =new User();
        user.setRole(Role.ROLE_ADMIN);
        user.setEmail("kurbanov@gmail.com");
        user.setPassword(passwordEncoder.encode("123123"));
        admin.setUser(user);
        adminRepository.save(admin);
    }

}
