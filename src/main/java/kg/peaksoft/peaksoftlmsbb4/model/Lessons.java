package kg.peaksoft.peaksoftlmsbb4.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "lessons")
@Getter
@Setter
public class Lessons {
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
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "task_id")
    private List<Task> tasks;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "link_id")
    private List<Link> links;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "video_lessons_id")
    private List<VideoLesson> videoLessons;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "presentation_id")
    private List<Presentation> presentations;

    public void setPresentation(Presentation presentation){
        if(presentations == null){
            presentations = new ArrayList<>();
        }
        presentations.add(presentation);
    }

    public void setLink(Link link){
        if(links == null){
           links = new ArrayList<>();
        }
        links.add(link);
    }

    public void setTask(Task task){
        if(tasks == null){
            tasks = new ArrayList<>();
        }
        tasks.add(task);
    }

    public void setVideoLesson(VideoLesson videoLesson){
        if(videoLessons == null){
            videoLessons = new ArrayList<>();
        }
        videoLessons.add(videoLesson);
    }


}
