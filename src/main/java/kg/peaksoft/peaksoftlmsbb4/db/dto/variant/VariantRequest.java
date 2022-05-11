package kg.peaksoft.peaksoftlmsbb4.db.dto.variant;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VariantRequest {
    @JsonProperty("option")
    private String option;
    @JsonProperty("answer")
    private Boolean choiceAnswer;
}
