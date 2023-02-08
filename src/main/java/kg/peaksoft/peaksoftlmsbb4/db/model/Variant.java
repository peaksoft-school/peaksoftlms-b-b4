package kg.peaksoft.peaksoftlmsbb4.db.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.CascadeType.*;

@Getter
@Setter
@Entity
@Table(name = "variants")
public class Variant {

    @Id
    @SequenceGenerator(name = "variant_id_gen", sequenceName = "variant_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "variant_id_gen")
    private Long id;

    private String option;
    private Boolean answer = false;

    @ManyToOne(cascade = {MERGE, REFRESH, DETACH})
    @JoinColumn(name = "question_id")
    private Question question;

}