package kg.peaksoft.peaksoftlmsbb4.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "lessons")
    private List<Task> tasks;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "lessons")
    private List<Link> links;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "lessons")
    private List<VideoLesson> videoLessons;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "lessons")
    private List<Presentation> presentations;
    @ManyToOne(cascade = {MERGE,REFRESH,DETACH,PERSIST})
    @JoinColumn(name = "course_id")
    private Course courses;

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
