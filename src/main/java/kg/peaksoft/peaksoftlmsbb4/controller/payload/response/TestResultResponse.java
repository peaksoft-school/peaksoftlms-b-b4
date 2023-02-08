package kg.peaksoft.peaksoftlmsbb4.controller.payload.response;

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
