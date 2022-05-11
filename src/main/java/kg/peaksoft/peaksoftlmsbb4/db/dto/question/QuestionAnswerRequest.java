package kg.peaksoft.peaksoftlmsbb4.db.dto.question;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class QuestionAnswerRequest {
    private Long questionId;
    private List<Long> variantId;
}
