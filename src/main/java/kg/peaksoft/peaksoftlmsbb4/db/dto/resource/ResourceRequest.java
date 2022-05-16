package kg.peaksoft.peaksoftlmsbb4.db.dto.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import kg.peaksoft.peaksoftlmsbb4.db.enums.ResourceType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@AllArgsConstructor
public class ResourceRequest {
    private ResourceType resourceType;
    private String value;
}
