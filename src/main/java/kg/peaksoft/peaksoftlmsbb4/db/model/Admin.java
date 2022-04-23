package kg.peaksoft.peaksoftlmsbb4.db.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.CascadeType.*;

@Entity
@Table(name = "admins")
@Getter
@Setter
public class Admin {
    @Id
    @SequenceGenerator(
            name = "admins_id_seq",
            sequenceName = "admins_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "admins_id_seq"
    )
    private Long id;

    private String firstName;

    private String lastName;

    @OneToOne(cascade = {MERGE, REFRESH, PERSIST}, orphanRemoval = true,
            fetch = FetchType.EAGER)
    private User user;
}
