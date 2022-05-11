package kg.peaksoft.peaksoftlmsbb4.db.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
            name = "course_id_seq",
            sequenceName = "course_id_seq",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "course_id_seq"
    )
    private Long id;
    private String courseName;
    private String image;
    private String description;
    private LocalDate dateOfStart;
    private LocalDate dateOfFinish;

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
    private List<Teacher> teachers = new ArrayList<>();

    @ManyToMany(cascade = ALL)
    @JoinTable(name = "courses_groups",
            joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "courses_id"))
    private List<Group> groups = new ArrayList<>();

    @OneToMany(cascade = ALL, mappedBy = "courses")
    private List<Lesson> lessons = new ArrayList<>();

    public Course(String courseName, String image, String description, LocalDate dateOfStart) {
        this.courseName = courseName;
        this.image = image;
        this.description = description;
        this.dateOfStart = dateOfStart;
    }

    public Course() {

    }

    public void addTeacher(Teacher teacher) {
        if (this.teachers == null) {
            this.teachers = new ArrayList<>();
        }
        this.teachers.add(teacher);
        teacher.addCourse(this);
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

    public void setLesson(Lesson lesson) {
        if (lessons == null) {
            lessons = new ArrayList<>();
        }
        lessons.add(lesson);
        lesson.setCourses(this);
    }

    public Course(String courseName,
                  String image,
                  String description,
                  LocalDate dateOfStart,
                  LocalDate dateOfFinish,
                  List<Student> students,
                  List<Teacher> teachers,
                  List<Group> groups,
                  List<Lesson> lessons) {
        this.courseName = courseName;
        this.image = image;
        this.description = description;
        this.dateOfStart = dateOfStart;
        this.dateOfFinish = dateOfFinish;
        this.students = students;
        this.teachers = teachers;
        this.groups = groups;
        this.lessons = lessons;
    }
}

