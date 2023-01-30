package kg.peaksoft.peaksoftlmsbb4.controller.payload.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AnswerResponse {
    private Long id;
    private int grade;
    private List<AnswerResultResponse> answerResultResponses;
}
