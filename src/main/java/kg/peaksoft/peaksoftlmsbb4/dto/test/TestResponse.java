package kg.peaksoft.peaksoftlmsbb4.dto.test;

import com.fasterxml.jackson.annotation.JsonProperty;
import kg.peaksoft.peaksoftlmsbb4.dto.question.QuestionResponse;
import kg.peaksoft.peaksoftlmsbb4.model.Question;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TestResponse {
    private Long id;
    @JsonProperty("test_name")
    private String testName;
    private List<QuestionResponse>questionResponses;

}
