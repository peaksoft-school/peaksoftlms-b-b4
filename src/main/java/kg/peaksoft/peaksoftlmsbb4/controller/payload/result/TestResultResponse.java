package kg.peaksoft.peaksoftlmsbb4.controller.payload.result;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class TestResultResponse {
    private boolean testIsEnabled;
    private String name;
    private List<ResultResponse> resultResponses;
}
