package kg.peaksoft.peaksoftlmsbb4.api;

import kg.peaksoft.peaksoftlmsbb4.db.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("api/auth")
public class Api {
    @GetMapping("/test")
    public Map<String, String> test(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return Map.of(
                "email", authentication.getName(),
                "authority", user.getRole().name()
        );
    }
}
