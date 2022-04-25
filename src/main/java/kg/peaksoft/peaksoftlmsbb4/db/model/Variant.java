package kg.peaksoft.peaksoftlmsbb4.db.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "variants")
@Getter
@Setter
public class Variant {
    @Id
    @SequenceGenerator(
            name = "variant_id_seq",
            sequenceName = "variant_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "variant_id_seq"
    )
    private Long id;
    private String variantName;
    private Boolean isTrue = false;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "question_id")
    private Question question;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "result_id")
    private Result result;


}