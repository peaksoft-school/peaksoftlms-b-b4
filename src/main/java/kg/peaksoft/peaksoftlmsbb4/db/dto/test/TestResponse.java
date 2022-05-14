package kg.peaksoft.peaksoftlmsbb4.db.dto.test;

import com.fasterxml.jackson.annotation.JsonProperty;
import kg.peaksoft.peaksoftlmsbb4.db.dto.question.QuestionResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TestResponse {
    @JsonProperty("test_id")
    private Long id;
    @JsonProperty("test_name")
    private String testName;
    @JsonProperty("questions")
    private List<QuestionResponse> questionResponses;

}
