package kg.peaksoft.peaksoftlmsbb4.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.CascadeType.ALL;

@Entity
@Table(name = "admin")
@Getter
@Setter
public class Admin {
    @Id
    @SequenceGenerator(
            name = "admin_sequence",
            sequenceName = "admin_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "admin_sequence"
    )
    private Long id;

    private String firstName;

    private String lastName;

    @OneToOne(cascade = ALL,fetch = FetchType.EAGER)
    private User user;
}
