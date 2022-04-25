package kg.peaksoft.peaksoftlmsbb4.db.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "video_lessons")
@Getter
@Setter
public class VideoLesson {
    @Id
    @SequenceGenerator(
            name = "video_lesson_id_seq",
            sequenceName = "video_lesson_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "video_lesson_id_seq"
    )
    private Long id;
    private String name;
    private String description;
    private String link;

    @ManyToOne
    @JoinColumn(name = "lesson_id")
    private Lesson lessons;

}
