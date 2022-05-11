package kg.peaksoft.peaksoftlmsbb4.db.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kg.peaksoft.peaksoftlmsbb4.db.enums.StudyFormat;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "student")
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
    private String phoneNumber;
    @OneToOne(cascade = {MERGE, REFRESH, PERSIST}, orphanRemoval = true,
            fetch = FetchType.EAGER)
    private User user;

    @Enumerated(EnumType.STRING)
    private StudyFormat studyFormat;

    @ManyToOne(cascade = {REFRESH,DETACH,MERGE})
    @JsonIgnore
    @JoinColumn(name = "group_id")
    private Group group;

    @JsonIgnore
    @ManyToMany(cascade = {REFRESH, MERGE}, mappedBy = "students", fetch = FetchType.LAZY)
    private List<Course> courses;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private User user;
    
    @JsonIgnore
    public void setCourse(Course course) {
        if (course == null) {
            courses = new ArrayList<>();
        }
        courses.add(course);
    }


}
