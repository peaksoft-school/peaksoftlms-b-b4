package kg.peaksoft.peaksoftlmsbb4.db.dto.result;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResultResponse {
    private Long id;
    @JsonProperty("student_answer")
    private String studentAnswer;
}
