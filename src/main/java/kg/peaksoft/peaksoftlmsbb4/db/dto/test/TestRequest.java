package kg.peaksoft.peaksoftlmsbb4.db.dto.test;

import com.fasterxml.jackson.annotation.JsonProperty;
import kg.peaksoft.peaksoftlmsbb4.db.dto.question.QuestionRequest;
import kg.peaksoft.peaksoftlmsbb4.db.dto.question.QuestionResponse;
import kg.peaksoft.peaksoftlmsbb4.db.model.Question;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TestRequest {
    @JsonProperty("test_name")
    private String testName;
    @JsonProperty("is_enabled")
    private Boolean disable=false;
    @JsonProperty("lesson_id")
    private Long lessonsId;
    @JsonProperty("question_list")
    private List<QuestionRequest> questionRequestList;
}
