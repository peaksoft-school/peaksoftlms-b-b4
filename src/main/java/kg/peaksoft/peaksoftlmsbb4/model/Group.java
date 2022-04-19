package kg.peaksoft.peaksoftlmsbb4.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "groups")
@Getter
@Setter
public class Group {
    @Id
    @SequenceGenerator(
            name = "groups_id_seq",
            sequenceName = "groups_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "groups_id_seq"
    )
    private Long id;
    private String groupName;
    private String description;
    private String imagine;
    private LocalDate dateOfStart;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private List<Student> students = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH},mappedBy = "groups")
    private List<Course> courses = new ArrayList<>();

    @JsonIgnore
    public void setCourse(Course course) {
        if (course == null) {
            courses = new ArrayList<>();
        }
        courses.add(course);
        assert course != null;
        course.setGroup(this);
    }
}