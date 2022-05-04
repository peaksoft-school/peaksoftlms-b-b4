package kg.peaksoft.peaksoftlmsbb4.db.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.CascadeType.*;

@Entity
@Table(name = "tasks")
@Getter
@Setter
public class Task {
    @Id
    @SequenceGenerator(
            name = "task_id_seq",
            sequenceName = "task_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "task_id_seq"
    )
    private Long id;
    private String text;
    private String file;
    private String name;
    private String image;
    private String link;
    private String code;

    @OneToOne(cascade = {DETACH,REFRESH,MERGE},mappedBy = "task")
    private Lesson lessons;


}
