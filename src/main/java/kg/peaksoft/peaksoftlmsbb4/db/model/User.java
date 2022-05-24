package kg.peaksoft.peaksoftlmsbb4.db.model;

import kg.peaksoft.peaksoftlmsbb4.db.enums.Role;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;


@Entity
@Table(name = "users")
@Getter
@Setter
public class User implements UserDetails {

    @Id
    @SequenceGenerator(
            name = "users_id_seq",
            sequenceName = "users_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "users_id_seq"
    )
    private Long id;
    @Column(unique = true)
    private String email;
    private String password;
    private boolean isAccountNonExpired = true;
    private boolean isAccountNonLocked = true;
    private boolean isCredentialsNonExpired = true;
    private boolean isEnabled = true;

    @Enumerated(EnumType.STRING)
    private Role role;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(role);
    }

    @Override
    public String getUsername() {
        return getEmail();
    }

}
