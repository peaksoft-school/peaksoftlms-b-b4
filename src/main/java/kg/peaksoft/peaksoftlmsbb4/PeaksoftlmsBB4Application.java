package kg.peaksoft.peaksoftlmsbb4;

import kg.peaksoft.peaksoftlmsbb4.db.enums.Role;
import kg.peaksoft.peaksoftlmsbb4.db.enums.StudyFormat;
import kg.peaksoft.peaksoftlmsbb4.db.model.Student;
import kg.peaksoft.peaksoftlmsbb4.db.model.Teacher;
import kg.peaksoft.peaksoftlmsbb4.db.model.User;
import kg.peaksoft.peaksoftlmsbb4.db.repository.StudentRepository;
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
    private final StudentRepository studentRepository;

    public static void main(String[] args) {
        SpringApplication.run(PeaksoftlmsBB4Application.class, args);
        System.out.println("Welcome colleagues, project name is Peaksoftlms-B");
    }

    @GetMapping("/")
    public String greetingPage() {
        return "<h1>Welcome to Peaksoftlms-B application!!!<h1/>";
    }
//
//    @Bean
//    public CommandLineRunner CommandLineRunnerBean() {
//        return (args) -> {
//            User user = new User();
//            user.setEmail("admin@gmail.com");
//            user.setPassword(encoder.encode("1234567"));
//            user.setRole(Role.ADMIN);
//            userRepository.save(user);
//        };
//    }
//    @Bean
//    public CommandLineRunner saveTeacherBean(){
//        return args -> {
//            User user=new User();
//            user.setEmail("teacher@gmail.com");
//            user.setPassword(encoder.encode("teacher"));
//            user.setRole(Role.TEACHER);
//            Teacher teacher=new Teacher();
//            teacher.setName("Chyngyz");
//            teacher.setLastName("Sharshakeev");
//            teacher.setPhoneNumber("0502044990");
//            teacher.setSpecialization("instructor");
//            teacher.setUser(user);
//            teacherRepository.save(teacher);
//        };
//    }
//    @Bean
//    public CommandLineRunner saveStudentBean(){
//        return args -> {
//            User user=new User();
//            user.setRole(Role.STUDENT);
//            user.setPassword(encoder.encode("student"));
//            user.setEmail("student@gmail.com");
//            Student student=new Student();
//            student.setStudentName("Rahim");
//            student.setStudyFormat(StudyFormat.ONLINE);
//            student.setPhoneNumber("0502044990");
//            student.setLastName("Kurbanov");
//            student.setUser(user);
//            studentRepository.save(student);
//        };
//    }
}





