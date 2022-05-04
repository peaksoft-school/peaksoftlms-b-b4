package kg.peaksoft.peaksoftlmsbb4.db.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.CascadeType.*;

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

    @OneToOne(cascade = {DETACH, MERGE, REFRESH}, mappedBy = "presentation")
    private Lesson lessons;

}
