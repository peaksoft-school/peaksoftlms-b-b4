package kg.peaksoft.peaksoftlmsbb4.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "options")
@Getter
@Setter
public class Option {
    @Id
    @SequenceGenerator(
            name = "option_id_seq",
            sequenceName = "option_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "option_id_seq"
    )
    private Long id;
    private String optionName;
    private Boolean answer;

}
