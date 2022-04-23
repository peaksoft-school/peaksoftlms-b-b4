package kg.peaksoft.peaksoftlmsbb4.db.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.*;

@Entity
@Table(name = "teachers")
@Getter
@Setter
public class Teacher {

    @Id
    @SequenceGenerator(
            name = "teachers_id_seq",
            sequenceName = "teachers_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "teachers_id_seq"
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
    @ManyToMany(cascade = {REFRESH, MERGE}, mappedBy = "teachers", fetch = FetchType.LAZY)
    private List<Course> courses;

    @JsonIgnore
    public void setCourse(Course course) {
        if (course == null) {
            courses = new ArrayList<>();
        }
        courses.add(course);
    }

}
