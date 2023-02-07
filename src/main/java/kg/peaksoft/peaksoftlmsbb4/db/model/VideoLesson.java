package kg.peaksoft.peaksoftlmsbb4.db.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.CascadeType.*;

@Getter
@Setter
@Entity
@Table(name = "video_lessons")
public class VideoLesson {

    @Id
    @SequenceGenerator(name = "video_lesson_id_gen", sequenceName = "video_lesson_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "video_lesson_id_gen")
    private Long id;

    private String name;
    private String description;
    private String link;

    @OneToOne(cascade = {DETACH, MERGE, REFRESH}, mappedBy = "videoLesson")
    private Lesson lessons;

}
