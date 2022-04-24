package kg.peaksoft.peaksoftlmsbb4.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    private Boolean correct;
    @JsonIgnore
    @OneToMany(mappedBy = "result", cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH}, orphanRemoval = true)
    private List<Variant> variants = new ArrayList<>();

//    @ManyToMany(mappedBy = "results", cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
//    @OrderBy("answer")
//    private List<Option> options = new ArrayList<>();

}
