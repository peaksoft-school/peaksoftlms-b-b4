package kg.peaksoft.peaksoftlmsbb4.db.dto.question;

import com.fasterxml.jackson.annotation.JsonProperty;
import kg.peaksoft.peaksoftlmsbb4.db.dto.variant.VariantResponse;
import kg.peaksoft.peaksoftlmsbb4.db.enums.QuestionType;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class QuestionResponse {
    private Long id;
    private String question;
    @JsonProperty("question_type")
    private QuestionType questionType;
    private List<VariantResponse> variantResponses;

}
