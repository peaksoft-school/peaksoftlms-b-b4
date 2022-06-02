package kg.peaksoft.peaksoftlmsbb4.db.dto.resource;

import kg.peaksoft.peaksoftlmsbb4.db.enums.ResourceType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResourceRequest {
    private String name;
    private ResourceType resourceType;
    private String value;
}
