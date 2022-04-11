package kg.peaksoft.peaksoftlmsbb4.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.CascadeType.ALL;

@Entity
@Table(name = "teachers")
@Getter
@Setter
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String teacherName;

    private String lastName;

    private String phoneNumber;

    private String specialization;

    @OneToOne(cascade = ALL,fetch = FetchType.EAGER)
    private User user;

}
