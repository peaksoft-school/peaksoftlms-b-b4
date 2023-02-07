package kg.peaksoft.peaksoftlmsbb4.db.model;

import kg.peaksoft.peaksoftlmsbb4.db.enums.QuestionType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "questions")
public class Question {

    @Id
    @SequenceGenerator(name = "question_id_gen", sequenceName = "question_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "question_id_gen")
    private Long id;

    private String question;

    @Enumerated(EnumType.STRING)
    private QuestionType questionType;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<Variant> variants;

    public void setVariants(List<Variant> variant) {
        if (variants == null) {
            variants = variant;
        }
        for (Variant q : variant) {
            q.setQuestion(this);
        }
    }

}