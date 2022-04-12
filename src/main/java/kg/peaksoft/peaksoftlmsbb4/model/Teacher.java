package kg.peaksoft.peaksoftlmsbb4.model;

import lombok.Data;
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
    @SequenceGenerator(
            name = "teacher_sequence",
            sequenceName = "teacher_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "teacher_sequence"
    )
    private Long id;

    private String teacherName;

    private String lastName;

    private String phoneNumber;

    private String specialization;

    @OneToOne(cascade = ALL,fetch = FetchType.EAGER)
    private User user;

}
