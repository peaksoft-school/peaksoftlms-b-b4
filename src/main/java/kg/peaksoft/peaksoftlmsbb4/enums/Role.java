package kg.peaksoft.peaksoftlmsbb4.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_ADMIN,
    TEACHER,
    STUDENT;
    @Override
    public String getAuthority() {
        return name();
    }
}
