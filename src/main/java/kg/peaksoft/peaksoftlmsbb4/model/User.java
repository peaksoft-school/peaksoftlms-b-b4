package kg.peaksoft.peaksoftlmsbb4.model;

import kg.peaksoft.peaksoftlmsbb4.enums.Role;
import lombok.Data;

import javax.persistence.*;


@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Enumerated(EnumType.STRING)
    private String email;
    private String password;
    private Role role;



}
