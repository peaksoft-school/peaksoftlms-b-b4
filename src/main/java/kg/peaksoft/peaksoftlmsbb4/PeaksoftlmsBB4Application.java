package kg.peaksoft.peaksoftlmsbb4;

import kg.peaksoft.peaksoftlmsbb4.db.enums.Role;
import kg.peaksoft.peaksoftlmsbb4.db.model.Teacher;
import kg.peaksoft.peaksoftlmsbb4.db.model.User;
import kg.peaksoft.peaksoftlmsbb4.db.repository.TeacherRepository;
import kg.peaksoft.peaksoftlmsbb4.db.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@AllArgsConstructor
public class PeaksoftlmsBB4Application {

    private final PasswordEncoder encoder;
    private final UserRepository userRepository;
    private final TeacherRepository teacherRepository;

    public static void main(String[] args) {
        SpringApplication.run(PeaksoftlmsBB4Application.class, args);
        System.out.println("Welcome colleagues, project name is Peaksoftlms-B");
    }

    @GetMapping("/")
    public String greetingPage() {
        return "<h1>Welcome to Peaksoftlms-B application!!!<h1/>";
    }

    @Bean
    public CommandLineRunner CommandLineRunnerBean() {
        return (args) -> {
            User user = new User();
            user.setEmail("admin@gmail.com");
            user.setPassword(encoder.encode("admin"));
            user.setRole(Role.ADMIN);
            if (!userRepository.existsByEmail(user.getEmail())) {
                userRepository.save(user);
            }

            User teacher = new User();
            teacher.setEmail("chyngyz@gmail.com");
            teacher.setPassword(encoder.encode("teacher"));
            teacher.setRole(Role.INSTRUCTOR);
            Teacher teacher1 = new Teacher();
            teacher1.setName("Chyngyz");
            teacher1.setLastName("Sharshekeev");
            teacher1.setPhoneNumber("0777777777");
            teacher1.setSpecialization("Java developer");
            teacher1.setUser(teacher);
            if (!userRepository.existsByEmail(teacher.getEmail())) {
                teacherRepository.save(teacher1);
            }
        };
    }

}






