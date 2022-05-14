package kg.peaksoft.peaksoftlmsbb4.db.dto.variant;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VariantResponse {
    @JsonProperty("variant_id")
    private Long id;
    @JsonProperty("option")
    private String option;

}
