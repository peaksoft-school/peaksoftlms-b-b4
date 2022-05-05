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

    @OneToOne(mappedBy = "lessons", cascade = ALL, orphanRemoval = true)
    private Test test;

    public void setPresentation(Presentation presentation) {
        if (presentations == null) {
            presentations = new ArrayList<>();
        }
        presentations.add(presentation);
        presentation.setLessons(this);
    }

    public void setLink(Link link) {
        if (links == null) {
            links = new ArrayList<>();
        }
        links.add(link);
        link.setLessons(this);
    }

    public void setTask(Task task) {
        if (tasks == null) {
            tasks = new ArrayList<>();
        }
        tasks.add(task);
        task.setLessons(this);
    }

    public void setVideoLesson(VideoLesson videoLesson) {
        if (videoLessons == null) {
            videoLessons = new ArrayList<>();
        }
        videoLessons.add(videoLesson);
        videoLesson.setLessons(this);
    }


}
