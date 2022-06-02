package kg.peaksoft.peaksoftlmsbb4.db.dto.test;

import kg.peaksoft.peaksoftlmsbb4.db.dto.question.QuestionRequest;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TestRequest {
    private String testName;
    private Boolean disable = false;
    private Long lessonsId;
    private List<QuestionRequest> questionRequestList;
}
