package kg.peaksoft.peaksoftlmsbb4.db.dto.result;

import com.fasterxml.jackson.annotation.JsonProperty;
import kg.peaksoft.peaksoftlmsbb4.db.model.Question;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.cfg.QuerySecondPass;

import java.util.List;

@Getter
@Setter
public class ResultRequest {
    @JsonProperty("student_answer")
    private String studentAnswer;
    @JsonProperty("is_true")
    private Boolean isTrue;
    @JsonProperty("variant_id")
    private Long variantId;
    @JsonProperty("question_id")
    private Long questionId;
}
