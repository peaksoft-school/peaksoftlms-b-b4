package kg.peaksoft.peaksoftlmsbb4.db.dto.test;

import kg.peaksoft.peaksoftlmsbb4.db.dto.question.QuestionResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TestResponse {
    private Long id;
    private String testName;
    private List<QuestionResponse> questionResponses;
}
