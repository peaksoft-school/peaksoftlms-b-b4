package kg.peaksoft.peaksoftlmsbb4.db.model;

import kg.peaksoft.peaksoftlmsbb4.db.enums.ResourceType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "resources")
public class Resource {

    @Id
    @SequenceGenerator(name = "resource_id_gen", sequenceName = "resource_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "resource_id_gen")
    private Long id;

    private String name;
    private ResourceType resourceType;

    @Column(length = 10000000)
    private String value;

    public Resource(ResourceType resourceType, String value, String name) {
        this.resourceType = resourceType;
        this.value = value;
        this.name = name;
    }

}
