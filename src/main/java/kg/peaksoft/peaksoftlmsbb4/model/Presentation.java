package kg.peaksoft.peaksoftlmsbb4.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "presentation")
@Getter
@Setter
public class Presentation {
    @Id
    @SequenceGenerator(
            name = "presentation_id_seq",
            sequenceName = "presentation_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "presentation_id_seq"
    )
    private Long id;
    private String name;
    private String description;
    private String file;

    @ManyToOne
    @JoinColumn(name = "lesson_id")
    private Lesson lessons;

}
