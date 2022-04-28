package kg.peaksoft.peaksoftlmsbb4.db.dto.test;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TestRequest {
    @JsonProperty("test_name")
    private String testName;
    @JsonProperty("lesson_id")
    private Long lessonsId;
}
