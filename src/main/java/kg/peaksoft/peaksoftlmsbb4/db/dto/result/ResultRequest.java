package kg.peaksoft.peaksoftlmsbb4.db.dto.result;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResultRequest {
    @JsonProperty("student_answer")
    private String studentAnswer;
    @JsonProperty("is_true")
    private Boolean isTrue;
    @JsonProperty("variant_id")
    private Long variantId;
}
