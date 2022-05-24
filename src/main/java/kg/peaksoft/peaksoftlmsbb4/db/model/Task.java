package kg.peaksoft.peaksoftlmsbb4.db.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

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
    private String name;

    @OneToMany(cascade = ALL)
    private List<Resource> resources;


    @OneToOne(cascade = {REMOVE, DETACH, REFRESH, MERGE}, mappedBy = "task")
    private Lesson lessons;

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", resources=" + resources +
                '}';
    }
}
