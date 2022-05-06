package kg.peaksoft.peaksoftlmsbb4.db.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "results")
@Getter
@Setter
public class Result {
    @Id
    @SequenceGenerator(
            name = "result_id_seq",
            sequenceName = "result_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "result_id_seq"
    )
    private Long id;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "student_id")
    private Student student;

    @OneToMany(mappedBy = "result", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Variant> variants;

}