package kg.peaksoft.peaksoftlmsbb4.dto.test;

import com.fasterxml.jackson.annotation.JsonProperty;
import kg.peaksoft.peaksoftlmsbb4.model.Lessons;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TestRequest {
    @JsonProperty(namespace = "test_name")
    private String testName;
}
