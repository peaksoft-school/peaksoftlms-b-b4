package kg.peaksoft.peaksoftlmsbb4.model;

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


}
