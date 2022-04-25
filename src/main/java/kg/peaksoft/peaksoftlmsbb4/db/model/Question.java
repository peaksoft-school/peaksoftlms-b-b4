package kg.peaksoft.peaksoftlmsbb4.model;

import kg.peaksoft.peaksoftlmsbb4.enums.QuestionType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "questions")
@Getter
@Setter
public class Question {
    @Id
    @SequenceGenerator(
            name = "question_id_seq",
            sequenceName = "question_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "question_id_seq"
    )
    private Long id;
    private String question;
    @Enumerated(EnumType.STRING)
    private QuestionType questionType;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "test_id")
    private Test test;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Variant> variants = new ArrayList<>();

    public void setVariants(Variant variant) {
        if (variants == null) {
            variants = new ArrayList<>();
        }
        variants.add(variant);
        variant.setQuestion(this);
    }

}