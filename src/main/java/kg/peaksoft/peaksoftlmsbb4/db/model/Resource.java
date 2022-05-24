package kg.peaksoft.peaksoftlmsbb4.db.model;

import kg.peaksoft.peaksoftlmsbb4.db.enums.ResourceType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.CascadeType.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Resource {

    @Id
    @SequenceGenerator(
            name = "resource_id_seq",
            sequenceName = "resource_id_seq",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "resource_id_seq"
    )
    private Long id;
    private ResourceType resourceType;
    private String value;

    public Resource(ResourceType resourceType, String value) {
        this.resourceType = resourceType;
        this.value = value;
    }

    @Override
    public String toString() {
        return "Resource{" +
                "id=" + id +
                ", resourceType=" + resourceType +
                ", value='" + value + '\'' +
                '}';
    }
}
