package kg.peaksoft.peaksoftlmsbb4.db.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tests")
@Getter
@Setter
public class Test {
    @Id
    @SequenceGenerator(
            name = "tests_id_seq",
            sequenceName = "tests_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "tests_id_seq"
    )
    private Long id;
    private String testName;
    private Boolean isEnabled;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Question> questions;

    @JsonIgnore
    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "lessons_id")
    private Lesson lessons;

//    public void setQuestions(List<Question> question) {
//        if (questions == null) {
//            questions = question;
//        }
//    }

}