package kg.peaksoft.peaksoftlmsbb4.db.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.CascadeType.*;

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

    @OneToOne(cascade = {DETACH, MERGE, REFRESH}, mappedBy = "link")
    private Lesson lessons;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

}
