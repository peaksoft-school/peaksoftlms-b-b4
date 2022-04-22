package kg.peaksoft.peaksoftlmsbb4.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "links")
@Getter
@Setter
public class Link {
    @Id
    @SequenceGenerator(
            name = "links_id_seq",
            sequenceName = "links_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "links_id_seq"
    )
    private Long id;
    private String text;
    private String link;

    @ManyToOne
    @JoinColumn(name = "lesson_id")
    private Lesson lessons;
}
