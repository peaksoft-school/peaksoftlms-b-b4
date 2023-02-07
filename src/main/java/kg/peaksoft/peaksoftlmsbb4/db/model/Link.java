package kg.peaksoft.peaksoftlmsbb4.db.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.CascadeType.*;

@Getter
@Setter
@Entity
@Table(name = "links")
public class Link {

    @Id
    @SequenceGenerator(name = "links_id_gen", sequenceName = "links_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "links_id_gen")
    private Long id;

    private String text;
    private String link;

    @OneToOne(cascade = {DETACH, MERGE, REFRESH}, mappedBy = "link")
    private Lesson lessons;

}
