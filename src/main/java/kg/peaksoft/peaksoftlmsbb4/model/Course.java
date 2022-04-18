package kg.peaksoft.peaksoftlmsbb4.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.*;

@Entity
@Table(name = "course")
@Getter
@Setter
public class Course {

    @Id
    @SequenceGenerator(
            name = "course_sequence",
            sequenceName = "course_sequence",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "course_sequence"
    )
    private Long id;
    private String courseName;
    private String image;
    private String description;
    private LocalDate dateOfStart;

    @ManyToMany(cascade =
            {PERSIST, REFRESH, DETACH, MERGE, REMOVE})
    @JoinTable(name = "courses_students",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    private List<Student> students;

    @ManyToMany(cascade =
            {PERSIST, REFRESH, DETACH, MERGE, REMOVE})
    @JoinTable(name = "course_teacher",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "teacher_id"))
    private List<Teacher> teachers;

    @ManyToMany(mappedBy = "courses", cascade = ALL)
    private List<Group> groups = new ArrayList<>();

    public void addStudent(Teacher teacher) {
        if (teacher == null) {
            teachers = new ArrayList<>();
        }
        teachers.add(teacher);
    }

    public void setGroup(Group group) {
        if (group == null) {
            groups = new ArrayList<>();
        }
        groups.add(group);
    }

    public void addStudent(Student student) {
        if (student == null) {
            students = new ArrayList<>();
        }
        students.add(student);
    }

}
