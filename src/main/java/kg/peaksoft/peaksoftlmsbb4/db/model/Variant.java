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
    private String option;
    private Boolean answer = false;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "question_id")
    private Question question;

    @Override
    public String toString() {
        return "Variant{" +
                "id=" + id +
                ", option='" + option + '\'' +
                ", answer=" + answer +
                '}';
    }
}