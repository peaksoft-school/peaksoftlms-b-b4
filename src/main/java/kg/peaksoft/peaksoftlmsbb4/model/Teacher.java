package kg.peaksoft.peaksoftlmsbb4.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.CascadeType.*;

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

    private String name;

    private String lastName;

    private String phoneNumber;

    private String specialization;

    @OneToOne(cascade = {MERGE, REFRESH, PERSIST}, orphanRemoval = true,
            fetch = FetchType.EAGER)

    private User user;

    @JsonIgnore
    @ManyToMany(cascade = {PERSIST,REFRESH,MERGE},mappedBy = "teachers",fetch = FetchType.LAZY)
    private List<Course>courses;

}
