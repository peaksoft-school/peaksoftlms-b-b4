package kg.peaksoft.peaksoftlmsbb4.controller.payload.result;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AnswerRequest {
    private Long testId;
    private List<QuestionAnswerRequest> questionAnswers;
}
