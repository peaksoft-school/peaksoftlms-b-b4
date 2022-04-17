package kg.peaksoft.peaksoftlmsbb4;

import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@AllArgsConstructor
public class PeaksoftlmsBB4Application {

    @GetMapping("/")
    public String greetingPage(){
        return "<h1>Welcome to Peaksoftlms-B application!!!<h1/>";
    }

    public static void main(String[] args) {
        SpringApplication.run(PeaksoftlmsBB4Application.class, args);
        System.out.println("Welcome colleagues, project name is Peaksoftlms-B");
    }
}




