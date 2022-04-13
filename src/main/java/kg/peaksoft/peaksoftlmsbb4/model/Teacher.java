package kg.peaksoft.peaksoftlmsbb4.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.CascadeType.*;
import static javax.persistence.CascadeType.PERSIST;

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

    @OneToOne(cascade = {MERGE, REFRESH,PERSIST}, orphanRemoval = true,
            fetch = FetchType.EAGER)
    @JoinColumn(name = "auth_info_id")
    private User user;

}
