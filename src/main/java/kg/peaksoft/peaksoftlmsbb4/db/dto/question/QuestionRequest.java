package kg.peaksoft.peaksoftlmsbb4.db.dto.question;

import com.fasterxml.jackson.annotation.JsonProperty;
import kg.peaksoft.peaksoftlmsbb4.db.enums.QuestionType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionRequest {
    private String question;
    @JsonProperty("question_type")
    private QuestionType questionType;
    @JsonProperty("test_id")
    private Long testId;
}
