package kg.peaksoft.peaksoftlmsbb4.controller.payload.test;

import kg.peaksoft.peaksoftlmsbb4.controller.payload.question.QuestionResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TestResponse {
    private Long id;
    private String testName;
    private boolean isEnabled;
    private List<QuestionResponse> questionResponses;
}
