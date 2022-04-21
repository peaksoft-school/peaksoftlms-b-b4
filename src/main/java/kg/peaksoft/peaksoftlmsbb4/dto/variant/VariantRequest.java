package kg.peaksoft.peaksoftlmsbb4.dto.variant;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kg.peaksoft.peaksoftlmsbb4.model.Question;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VariantRequest {
    private String variantName;
    private Boolean isTrue;
    @JsonIgnore
    private Question question;
}
