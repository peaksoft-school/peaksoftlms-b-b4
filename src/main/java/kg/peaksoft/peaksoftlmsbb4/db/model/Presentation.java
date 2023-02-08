package kg.peaksoft.peaksoftlmsbb4.db.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.CascadeType.*;

@Getter
@Setter
@Entity
@Table(name = "presentations")
public class Presentation {

    @Id
    @SequenceGenerator(name = "presentation_id_gen", sequenceName = "presentation_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "presentation_id_gen")
    private Long id;

    private String name;
    private String description;
    private String file;

    @OneToOne(cascade = {DETACH, MERGE, REFRESH}, mappedBy = "presentation")
    private Lesson lessons;

}
