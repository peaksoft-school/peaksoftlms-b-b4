package kg.peaksoft.peaksoftlmsbb4.db.dto.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import kg.peaksoft.peaksoftlmsbb4.db.enums.ResourceType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResourceResponse {
    @JsonProperty("resource_type")
    private ResourceType resourceType;
    private String value;
}