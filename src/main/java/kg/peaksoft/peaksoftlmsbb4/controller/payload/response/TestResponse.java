package kg.peaksoft.peaksoftlmsbb4.controller.payload.response;

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
