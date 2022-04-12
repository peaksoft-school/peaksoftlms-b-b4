package kg.peaksoft.peaksoftlmsbb4.model;

import kg.peaksoft.peaksoftlmsbb4.enums.Role;
import lombok.Data;

import javax.persistence.*;


@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private Long id;
    private String email;
    private String password;
    private Role role;



}
