package kg.peaksoft.peaksoftlmsbb4.controller.payload.request;

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
