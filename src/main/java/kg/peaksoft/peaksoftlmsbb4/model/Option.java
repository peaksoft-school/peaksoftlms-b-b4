package kg.peaksoft.peaksoftlmsbb4.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "options")
@Getter
@Setter
public class Option {
    @Id
    @SequenceGenerator(
            name = "option_id_seq",
            sequenceName = "option_id_seq",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "option_id_seq"
    )
    private Long id;
    private String optionName;
    private Boolean answer;

//    @ManyToMany(mappedBy = "options", cascade = {CascadeType.MERGE, CascadeType.REFRESH})
//    private List<Variant> variants = new ArrayList<>();
//
//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(name = "options_results",
//            joinColumns = @JoinColumn(name = "option_id"),
//            inverseJoinColumns = @JoinColumn(name = "results_id"))
//    private List<Result> results = new ArrayList<>();
//
//    public void setResults(Result result) {
//        if (results == null) {
//            results = new ArrayList<>();
//        }
//        results.add(result);
//    }

}