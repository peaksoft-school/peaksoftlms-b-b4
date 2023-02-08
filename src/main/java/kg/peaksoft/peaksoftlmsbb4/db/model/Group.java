package kg.peaksoft.peaksoftlmsbb4.db.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.*;

@Getter
@Setter
@Entity
@Table(name = "groups")
public class Group {

    @Id
    @SequenceGenerator(name = "groups_id_gen", sequenceName = "groups_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "groups_id_gen")
    private Long id;

    private String groupName;
    private String description;
    private String image;
    private LocalDate dateOfStart;
    private LocalDate dateOfFinish;

    @OneToMany(mappedBy = "group", cascade = ALL)
    private List<Student> students = new ArrayList<>();

    @ManyToMany(cascade = {MERGE, REFRESH, DETACH}, mappedBy = "groups")
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

    public void setStudent(Student student) {
        if (student == null) {
            students = new ArrayList<>();
        }
        students.add(student);
    }

}