package kg.peaksoft.peaksoftlmsbb4.db.model;

import kg.peaksoft.peaksoftlmsbb4.db.enums.Result;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.checkerframework.checker.units.qual.C;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.CascadeType.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Results {
    @Id
    @SequenceGenerator(
            name = "result_id_seq",
            sequenceName = "result_id_seq",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "result_id_seq"
    )
    private Long id;

    @Enumerated(EnumType.STRING)
    private Result result;

    private LocalDateTime dateOfPassed;

    private int grade;

    @OneToOne(cascade = {DETACH,MERGE,REFRESH,PERSIST})
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(cascade = {DETACH,MERGE,REFRESH,PERSIST})
    @JoinColumn(name = "test_id")
    private Test test;
}
