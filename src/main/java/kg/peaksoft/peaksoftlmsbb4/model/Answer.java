package kg.peaksoft.peaksoftlmsbb4.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "answers")
@Getter
@Setter
public class Answer {
    @Id
    @SequenceGenerator(
            name = "answer_id_seq",
            sequenceName = "answer_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "answer_id_seq"
    )
    private Long id;
    private String answer;


    @ManyToMany(mappedBy = "answers", cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    private List<Variant> variants = new ArrayList<>();

}