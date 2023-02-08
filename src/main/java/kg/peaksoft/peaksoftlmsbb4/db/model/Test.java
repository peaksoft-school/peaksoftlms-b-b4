package kg.peaksoft.peaksoftlmsbb4.db.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.CascadeType.*;

@Getter
@Setter
@Entity
@Table(name = "tests")
public class Test {

    @Id
    @SequenceGenerator(name = "tests_id_gen", sequenceName = "tests_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tests_id_gen")
    private Long id;

    private String testName;
    private Boolean isEnabled = true;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Question> questions;

    @JsonIgnore
    @OneToOne(cascade = {MERGE, REFRESH, DETACH}, mappedBy = "test")
    private Lesson lessons;

}