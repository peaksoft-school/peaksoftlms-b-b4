package kg.peaksoft.peaksoftlmsbb4.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kg.peaksoft.peaksoftlmsbb4.enums.Role;
import kg.peaksoft.peaksoftlmsbb4.enums.StudyFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.CascadeType.*;

@Entity
@Table(name = "student")
@Getter
@Setter

public class Student {

    @Id
    @SequenceGenerator(
            name = "student_id_seq",
            sequenceName = "student_id_seq",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_id_seq"
    )
    private Long id;
    private String studentName;
    private String lastName;
    private String email;
    private Role role;
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private StudyFormat studyFormat;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    @JsonIgnore
    @ManyToMany(cascade = {REFRESH,MERGE},mappedBy = "students",fetch = FetchType.LAZY)
    private List<Course> courses;

}
