package kg.peaksoft.peaksoftlmsbb4.db.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.CascadeType.*;

@Entity
@Table(name = "lessons")
@Getter
@Setter
public class Lesson {
    @Id
    @SequenceGenerator(
            name = "lessons_id_seq",
            sequenceName = "lessons_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "lessons_id_seq"
    )
    private Long id;
    private String name;
    @OneToOne(cascade = ALL)
    @JoinColumn(name = "task_id")
    private Task task;
    @OneToOne(cascade = ALL)
    @JoinColumn(name = "link_id")
    private Link link;
    @OneToOne(cascade = ALL)
    @JoinColumn(name = "video_lesson_id")
    private VideoLesson videoLesson;

    @OneToOne(cascade = ALL)
    @JoinColumn(name = "presentation_id")
    private Presentation presentation;
    @ManyToOne(cascade = {MERGE, REFRESH, DETACH, PERSIST})
    @JoinColumn(name = "course_id")
    private Course courses;

    @OneToOne(cascade = ALL)
    @JoinColumn(name = "test_id")
    private Test test;


}
